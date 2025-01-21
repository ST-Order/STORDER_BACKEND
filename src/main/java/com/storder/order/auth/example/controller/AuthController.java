package com.storder.order.auth.example.controller;

import com.storder.order.auth.example.docs.LoginAuthExceptionDocs;
import com.storder.order.auth.example.docs.RefreshAuthExceptionDocs;
import com.storder.order.global.annotation.ApiErrorExceptionsExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "로그인 인증 API")
@RestController
@RequestMapping("/api/v1/login/oauth2")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping(value = "/kakao", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "카카오 로그인 인증", description = "카카오를 통한 로그인 인증을 처리합니다.")
    @ApiErrorExceptionsExample(LoginAuthExceptionDocs.class)
    public ResponseEntity<String> loginWithKakaoCode(
            @Parameter(
                            description = "카카오에서 받아온 AuthorizationCode",
                            required = true,
                            example = "카카오 Auth 서버로부터 받은 코드")
                    @RequestParam
                    String code) {
        return ResponseEntity.ok("카카오 로그인 성공: " + code);
    }

    @PostMapping(value = "/refresh")
    @Operation(summary = "AccessToken 갱신", description = "Refresh Token을 통해 AccessToken을 갱신합니다.")
    @ApiErrorExceptionsExample(RefreshAuthExceptionDocs.class)
    public ResponseEntity<String> refresh(@RequestHeader("Refresh") String refreshToken) {
        return ResponseEntity.ok("새로운 AccessToken: " + refreshToken);
    }
}
