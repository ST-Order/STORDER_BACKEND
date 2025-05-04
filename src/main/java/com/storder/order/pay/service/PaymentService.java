package com.storder.order.pay.service;

import static com.storder.order.order.exception.OrderErrorCode.*;
import static com.storder.order.pay.exception.PayErrorCode.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.storder.order.global.dto.ApiResponse;
import com.storder.order.order.entity.Order;
import com.storder.order.order.exception.OrderException;
import com.storder.order.order.repository.OrderRepository;
import com.storder.order.pay.dto.user.*;
import com.storder.order.pay.entity.OutboxStatus;
import com.storder.order.pay.entity.Payment;
import com.storder.order.pay.entity.PaymentOutbox;
import com.storder.order.pay.entity.PaymentStatus;
import com.storder.order.pay.exception.PayException;
import com.storder.order.pay.repository.PaymentOutboxRepository;
import com.storder.order.pay.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Value("${kakaopay.secret-key}")
    private String kakaoPaySecretKey;

    @Value("${kakaopay.cid}")
    private String cid;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper;

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentOutboxRepository paymentOutboxRepository;

    /** 결제 준비 요청 (결제 정보 카카오페이에 전달) */
    public PaymentsReadyResponseDto requestPayment(PaymentsReadyRequestDto requestDto) {

        Order order =
                orderRepository
                        .findById(requestDto.getOrderId())
                        .orElseThrow(() -> new OrderException(ORDER_NOT_FOUND));

        if (!order.getFinalPayment().equals(requestDto.getTotalAmount())) {
            throw new PayException(INVALID_PAYMENT_AMOUNT);
        }

        // Todo: 설정파일로 뺄 것
        String baseUrl = "http://localhost";
        String approvalUrl = baseUrl + "/payments/approve?order_id=" + order.getOrderId();
        String cancelUrl = baseUrl + "/payments/cancel?order_id=" + order.getOrderId();
        String failUrl = baseUrl + "/payments/fail?order_id=" + order.getOrderId();

        KakaoReadyRequest kakaoReadyRequest =
                KakaoReadyRequest.builder()
                        .cid(cid)
                        .partnerOrderId(String.valueOf(order.getOrderId()))
                        .partnerUserId(String.valueOf(order.getUser().getUserId()))
                        .itemName(requestDto.getItemName())
                        .quantity(requestDto.getQuantity())
                        .totalAmount(requestDto.getTotalAmount())
                        .taxFreeAmount(requestDto.getTaxFreeAmount())
                        .approvalUrl(approvalUrl)
                        .cancelUrl(cancelUrl)
                        .failUrl(failUrl)
                        .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "DEV_SECRET_KEY " + kakaoPaySecretKey);

        HttpEntity<KakaoReadyRequest> request = new HttpEntity<>(kakaoReadyRequest, headers);

        ResponseEntity<KakaoReadyResponse> response =
                restTemplate.postForEntity(
                        "https://open-api.kakaopay.com/online/v1/payment/ready",
                        request,
                        KakaoReadyResponse.class);

        // 결제 정보 DB 저장
        Payment payment =
                Payment.builder()
                        .order(order)
                        .user(order.getUser())
                        .tid(response.getBody().getTid())
                        .paymentStatus(PaymentStatus.READY)
                        .totalAmount(requestDto.getTotalAmount())
                        .build();

        paymentRepository.save(payment);

        return PaymentsReadyResponseDto.of(response.getBody());
    }

    /** 결제 승인 요청 (사용자 결제 완료 후 호출) */
    @Transactional
    public ApiResponse<String> approvePaymentRequest(String pgToken, String orderIdStr) {
        Long orderId = Long.valueOf(orderIdStr);

        Order order =
                orderRepository
                        .findById(orderId)
                        .orElseThrow(() -> new OrderException(ORDER_NOT_FOUND));

        Payment payment =
                paymentRepository
                        .findByOrder(order)
                        .orElseThrow(() -> new PayException(PAYMENT_NOT_FOUND));

        KakaoApproveRequest approveRequest =
                KakaoApproveRequest.builder()
                        .cid(cid)
                        .tid(payment.getTid())
                        .partnerOrderId(String.valueOf(order.getOrderId()))
                        .partnerUserId(String.valueOf(order.getUser().getUserId()))
                        .pgToken(pgToken)
                        .build();

        try {
            String payload = objectMapper.writeValueAsString(approveRequest);

            PaymentOutbox outbox =
                    PaymentOutbox.builder()
                            .paymentId(payment.getPaymentId())
                            .eventType("PAYMENT_APPROVAL_REQUESTED")
                            .payload(payload)
                            .status(OutboxStatus.PENDING)
                            .build();

            paymentOutboxRepository.save(outbox);
            return ApiResponse.success("결제 승인 요청이 접수되었습니다.");
        } catch (JsonProcessingException e) {
            throw new PayException(PAYLOAD_SERIALIZATION_ERROR);
        }
    }

    /* TODO: 결제승인 api호출
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "SECRET_KEY " + kakaoPaySecretKey);

    HttpEntity<KakaoApproveRequest> request = new HttpEntity<>(approveRequest, headers);

    ResponseEntity<KakaoApproveResponse> response =
            restTemplate.postForEntity(
                    "https://open-api.kakaopay.com/online/v1/payment/approve",
                    request,
                    KakaoApproveResponse.class);

    */

}
