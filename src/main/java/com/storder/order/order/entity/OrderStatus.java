package com.storder.order.order.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.storder.order.order.exception.OrderErrorCode;
import com.storder.order.order.exception.OrderException;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum OrderStatus {
    UNPAY("결제 대기"), // 결제 대기
    PAY("결제 완료"), // 결제 완료
    COOKING("조리 중"), // 조리 중
    READY("준비 완료");// 준비 완료

    private final String name;

    private static final Map<String, OrderStatus> NAME_TO_ENUM_MAP = new HashMap<>();

    static {
        for (OrderStatus OrderStatus : OrderStatus.values()) {
            NAME_TO_ENUM_MAP.put(OrderStatus.name, OrderStatus);
        }
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static OrderStatus fromName(String name) {
        OrderStatus OrderStatus = NAME_TO_ENUM_MAP.get(name);

        if (OrderStatus == null) {
            throw new OrderException(OrderErrorCode.INVALID_ORDERSTATUS);
        }

        return OrderStatus;
    }
}


