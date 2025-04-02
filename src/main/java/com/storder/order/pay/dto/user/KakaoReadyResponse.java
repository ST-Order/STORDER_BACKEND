package com.storder.order.pay.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "카카오페이 결제 준비 응답 DTO")
public class KakaoReadyResponse {

    @Schema(description = "결제 고유 번호 (TID)", example = "T1234567890123456789")
    private String tid;

    @JsonProperty("next_redirect_app_url")
    @Schema(description = "앱 환경 리다이렉트 URL", example = "https://mockup-pg-web.kakao.com/xxx/app")
    private String nextRedirectAppUrl;

    @JsonProperty("next_redirect_mobile_url")
    @Schema(description = "모바일 웹 리다이렉트 URL", example = "https://mockup-pg-web.kakao.com/xxx/mobile")
    private String nextRedirectMobileUrl;

    @JsonProperty("next_redirect_pc_url")
    @Schema(description = "PC 웹 리다이렉트 URL", example = "https://mockup-pg-web.kakao.com/xxx/pc")
    private String nextRedirectPcUrl;

    @JsonProperty("android_app_scheme")
    @Schema(description = "안드로이드 앱 스킴", example = "kakaotalk://kakaopay/pg?url=...")
    private String androidAppScheme;

    @JsonProperty("ios_app_scheme")
    @Schema(description = "iOS 앱 스킴", example = "kakaotalk://kakaopay/pg?url=...")
    private String iosAppScheme;

    @JsonProperty("created_at")
    @Schema(description = "결제 준비 시각", example = "2023-07-15T21:18:22")
    private String createdAt;
}
