package com.storder.order.pay.entity;

import static com.storder.order.pay.exception.PayErrorCode.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.storder.order.pay.exception.PayException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentMethod {
    KAKAOPAY("카카오페이"),
    CARD("신용카드"),
    BANK_TRANSFER("계좌이체");

    private final String name;

    private static final Map<String, PaymentMethod> NAME_TO_ENUM_MAP = new HashMap<>();

    static {
        for (PaymentMethod method : PaymentMethod.values()) {
            NAME_TO_ENUM_MAP.put(method.name, method);
        }
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static PaymentMethod fromName(String name) {
        PaymentMethod method = NAME_TO_ENUM_MAP.get(name);

        if (method == null) {
            throw new PayException(INVALID_PAYMENT_METHOD);
        }

        return method;
    }
}
