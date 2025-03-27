package com.storder.order.pay.service;

import com.storder.order.pay.dto.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Value("${kakaopay.secret-key}")
    private String kakaoPaySecretKey;

    @Value("${kakaopay.cid}")
    private String cid;

    // DB에 저장해야 함
    private String cachedTid;

    private final RestTemplate restTemplate = new RestTemplate();

    /** 결제 준비 요청 (결제 정보 카카오페이에 전달) */
    public KakaoReadyResponse requestPayment(PaymentRequestDto requestDto) {

        // 필수값 설정
        requestDto =
                PaymentRequestDto.builder()
                        .partnerOrderId(requestDto.getPartnerOrderId())
                        .partnerUserId(requestDto.getPartnerUserId())
                        .itemName(requestDto.getItemName())
                        .quantity(requestDto.getQuantity())
                        .totalAmount(requestDto.getTotalAmount())
                        .taxFreeAmount(requestDto.getTaxFreeAmount())
                        .vatAmount(requestDto.getVatAmount())
                        .approvalUrl(requestDto.getApprovalUrl())
                        .cancelUrl(requestDto.getCancelUrl())
                        .failUrl(requestDto.getFailUrl())
                        .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "SECRET_KEY " + kakaoPaySecretKey);

        HttpEntity<PaymentRequestDto> request = new HttpEntity<>(requestDto, headers);

        ResponseEntity<KakaoReadyResponse> response =
                restTemplate.postForEntity(
                        "https://open-api.kakaopay.com/online/v1/payment/ready",
                        request,
                        KakaoReadyResponse.class);

        // 응답 TID 저장 (DB에 저장)
        this.cachedTid = response.getBody().getTid();

        return response.getBody();
    }

    /** 결제 승인 요청 (사용자 결제 완료 후 호출) */
    public KakaoApproveResponse approvePayment(String pgToken, Long orderId) {
        KakaoApproveRequest approveRequest =
                KakaoApproveRequest.builder()
                        .cid(cid)
                        .tid(this.cachedTid)
                        .partnerOrderId("order-" + orderId)
                        .partnerUserId("user-" + orderId)
                        .pgToken(pgToken)
                        .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "SECRET_KEY " + kakaoPaySecretKey);

        HttpEntity<KakaoApproveRequest> request = new HttpEntity<>(approveRequest, headers);

        ResponseEntity<KakaoApproveResponse> response =
                restTemplate.postForEntity(
                        "https://open-api.kakaopay.com/online/v1/payment/approve",
                        request,
                        KakaoApproveResponse.class);

        return response.getBody();
    }
}
