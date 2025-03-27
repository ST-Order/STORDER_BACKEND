package com.storder.order.pay.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.storder.order.pay.exception.PaymentErrorCode;
import com.storder.order.pay.exception.PaymentException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentStatus {
	READY("결제 준비"),
	APPROVED("결제 완료"),
	CANCELED("결제 취소"),
	FAILED("결제 실패");

	private final String name;

	private static final Map<String, PaymentStatus> NAME_TO_ENUM_MAP = new HashMap<>();

	static {
		for (PaymentStatus status : PaymentStatus.values()) {
			NAME_TO_ENUM_MAP.put(status.name, status);
		}
	}

	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static PaymentStatus fromName(String name) {
		PaymentStatus status = NAME_TO_ENUM_MAP.get(name);

		if (status == null) {
			throw new PaymentException(PaymentErrorCode.INVALID_PAYMENT_STATUS);
		}

		return status;
	}
}