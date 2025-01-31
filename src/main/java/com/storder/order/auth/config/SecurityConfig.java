package com.storder.order.auth.config;

import com.storder.order.auth.config.jwt.JwtAccessDeniedHandler;
import com.storder.order.auth.config.jwt.JwtAuthenticationEntryPoint;
import com.storder.order.auth.config.jwt.JwtAuthenticationFilter;
import com.storder.order.auth.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint)
            throws Exception {
        // CSRF 설정 비활성화
        http.csrf(csrf -> csrf.disable());

        // H2 Console 설정
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        // Form 로그인 비활성화
        http.formLogin(formLogin -> formLogin.disable());

        // HTTP Basic 인증 비활성화
        http.httpBasic(httpBasic -> httpBasic.disable());

        // URL별 접근 권한 설정
        http.authorizeHttpRequests(
                auth ->
                        auth.requestMatchers(
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/api/v1/users/**",
                                        "/api/auth/refresh-token",
                                        "/error",
                                        "/test/**",
                                        "/",
                                        "/api/v1/order/create",
                                        "/api/v1/notifications/**",
                                        "/api/v1/menus/**",
                                        "/api/v1/pay/**",
                                        "/api/v1/orders/**",
                                        "/api/v1/stores/**",
                                        "/api/v1/seller/stores/**",
                                        "/api/v1/seller/orders/**",
                                        "/api/v1/mypage/**",
                                        "/api/v1/sellers/**",
                                        "/api/v1/reviews/**",
                                        "/api/v1/payments/**",
                                        "/api/v1/auth/register",
                                        "/api/v1/auth/certification",
                                        "/api/v1/auth/verification",
                                        "/api/v1/auth/login",
                                        "/h2-console/**")
                                .permitAll()
                                .requestMatchers("/admin/**")
                                .hasRole("ADMIN")
                                .anyRequest()
                                .authenticated());

        // 세션 관리 설정 (Stateless로 설정)
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 인증되지 않은 사용자에 대한 응답 설정
        http.exceptionHandling(
                exception ->
                        exception
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint));

        // 커스텀 필터를 ID/PW 기반으로 인증하는 기본 필터 앞에 넣어 먼저 인증 시도
        http.addFilterBefore(
                new JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
