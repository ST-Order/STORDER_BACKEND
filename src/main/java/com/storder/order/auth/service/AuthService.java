package com.storder.order.auth.service;

import static com.storder.order.auth.exception.AuthErrorCode.*;

import com.storder.order.auth.dto.AuthRequestDto;
import com.storder.order.auth.dto.AuthResponseDto;
import com.storder.order.auth.exception.AuthException;
import com.univcert.api.UnivCert;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class AuthService {

    @Value("${univcert.api.key}")
    private String apiKey;

    public Boolean sendCertificationCode(String email) throws IOException {
        UnivCert.clear(apiKey, email);
        Map<String, Object> response = UnivCert.certify(apiKey, email, "서울과학기술대학교", false);

        if (response.get("success") == Boolean.FALSE) {
            throw new AuthException(SEND_CERTIFICATION_CODE_ERROR);
        }

        return true;
    }

    public AuthResponseDto.EmailVerification verifyCertificationCode(
            AuthRequestDto.EmailVerification request) throws IOException {
        Map<String, Object> response =
                UnivCert.certifyCode(apiKey, request.getEmail(), "서울과학기술대학교", request.getCode());

        if (response.get("success") == Boolean.FALSE) {
            throw new AuthException(VERIFY_CERTIFICATION_CODE_ERROR);
        }

        return AuthResponseDto.EmailVerification.of(response);
    }
}
