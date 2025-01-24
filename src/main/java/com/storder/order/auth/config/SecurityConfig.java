package com.storder.order.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
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
                                        "/api/v1/pay/**",
                                        "/api/v1/mypage/**",
                                        "/api/v1/sellers/**",
                                        "/h2-console/**")
                                .permitAll()
                                .requestMatchers("/admin/**")
                                .hasRole("ADMIN")
                                .anyRequest()
                                .authenticated());

        // 세션 관리 설정 (Stateless로 설정)
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
