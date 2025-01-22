package com.storder.order.user.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.storder.order.store.entity.UserRole;
import com.storder.order.store.exception.StoreErrorCode;
import com.storder.order.store.exception.StoreException;
import com.storder.order.user.exception.UserException;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum UserRole {
    USER("유저"),
    STORE("사장님");

    private final String name;

    private static final Map<String, UserRole> NAME_TO_ENUM_MAP = new HashMap<>();

    static {
        for (UserRole UserRole : UserRole.values()) {
            NAME_TO_ENUM_MAP.put(UserRole.name, UserRole);
        }
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static UserRole fromName(String name) {
        UserRole UserRole = NAME_TO_ENUM_MAP.get(name);

        if (UserRole == null) {
            throw new UserException(UserErrorCode.INVALID_USERROLE);
        }

        return UserRole;
    }
}
