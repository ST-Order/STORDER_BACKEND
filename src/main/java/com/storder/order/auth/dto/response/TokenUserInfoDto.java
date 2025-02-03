package com.storder.order.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenUserInfoDto {

    private String email;

    public static TokenUserInfoDto of(String email) {
        return TokenUserInfoDto.builder().email(email).build();
    }
}
