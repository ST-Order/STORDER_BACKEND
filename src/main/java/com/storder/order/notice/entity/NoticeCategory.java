package com.storder.order.notice.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.storder.order.notice.exception.NoticeErrorCode;
import com.storder.order.notice.exception.NoticeException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum NoticeCategory {
    OUT_OF_STOCK("품절"), // 품절
    INGREDIENT_SHORTAGE("재료소진"), // 재료소진
    BUSINESS_HOURS_CHANGE("운영시간 변경"), // 운영시간 변경
    NEW_MENU_RELEASE("신메뉴 출시"), // 신메뉴 출시
    MENU_DISCONTINUED("메뉴 단종"), // 메뉴 단종
    ETC("기타"); // 기타

    private final String name;

    private static final Map<String, NoticeCategory> NAME_TO_ENUM_MAP = new HashMap<>();

    static {
        for (NoticeCategory NoticeCategory : NoticeCategory.values()) {
            NAME_TO_ENUM_MAP.put(NoticeCategory.name, NoticeCategory);
        }
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static NoticeCategory fromName(String name) {
        NoticeCategory NoticeCategory = NAME_TO_ENUM_MAP.get(name);

        if (NoticeCategory == null) {
            throw new NoticeException(NoticeErrorCode.INVALID_NOTICE_CATEGORY);
        }

        return NoticeCategory;
    }
}
