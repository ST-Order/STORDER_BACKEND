package com.storder.order.notice.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.storder.order.notice.exception.NoticeErrorCode;
import com.storder.order.notice.exception.NoticeException;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum NoticeExposureTime {
    ONE_DAY("하루(24시간)"),
    ONE_WEEK("일주일(7일)"),
    ONE_MONTH("한달(30일)");
    
    private final String name;
    
    private static final Map<String, NoticeExposureTime> NAME_TO_ENUM_MAP = new HashMap<>();

    static {
        for (NoticeExposureTime NoticeExposureTime : NoticeExposureTime.values()) {
            NAME_TO_ENUM_MAP.put(NoticeExposureTime.name, NoticeExposureTime);
        }
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static NoticeExposureTime fromName(String name) {
        NoticeExposureTime NoticeExposureTime = NAME_TO_ENUM_MAP.get(name);

        if (NoticeExposureTime == null) {
            throw new NoticeException(NoticeErrorCode.INVALID_NOTICE_EXPOSURE_TIME);
        }

        return NoticeExposureTime;
    }
}
