package com.storder.order.pay.entity;

import static com.storder.order.pay.exception.PayErrorCode.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.storder.order.pay.exception.PayException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OutboxStatus {
    PENDING("발송 대기"),
    SENT("발송 완료"),
    FAILED("발송 실패");

    private final String name;

    private static final Map<String, OutboxStatus> NAME_TO_ENUM_MAP = new HashMap<>();

    static {
        for (OutboxStatus status : OutboxStatus.values()) {
            NAME_TO_ENUM_MAP.put(status.name, status);
        }
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static OutboxStatus fromName(String name) {
        OutboxStatus status = NAME_TO_ENUM_MAP.get(name);
        if (status == null) {
            throw new PayException(INVALID_OUTBOX_STATUS); // 아래 예외코드 추가 필요
        }
        return status;
    }
}
