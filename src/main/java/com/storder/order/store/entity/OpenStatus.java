package com.storder.order.store.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.storder.order.store.exception.StoreErrorCode;
import com.storder.order.store.exception.StoreException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OpenStatus {
    OPEN("영업중"),
    CLOSE("영업 종료");

    private final String name;

    private static final Map<String, OpenStatus> NAME_TO_ENUM_MAP = new HashMap<>();

    static {
        for (OpenStatus OpenStatus : OpenStatus.values()) {
            NAME_TO_ENUM_MAP.put(OpenStatus.name, OpenStatus);
        }
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static OpenStatus fromName(String name) {
        OpenStatus OpenStatus = NAME_TO_ENUM_MAP.get(name);

        if (OpenStatus == null) {
            throw new StoreException(StoreErrorCode.INVALID_OPEN_STATUS);
        }

        return OpenStatus;
    }
}
