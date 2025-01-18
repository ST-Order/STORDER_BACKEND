package com.storder.order.order.entity;

public enum OrderStatus {
	UNPAY,   // 결제 대기
	PAY,     // 결제 완료
	COOKING, // 조리 중
	READY    // 준비 완료
}