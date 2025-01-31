package com.storder.order.auth.service;

import static com.storder.order.auth.exception.AuthErrorCode.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.storder.order.auth.config.jwt.JwtTokenProvider;
import com.storder.order.auth.dto.request.AuthRequestDto;
import com.storder.order.auth.dto.response.AuthResponseDto;
import com.storder.order.auth.exception.AuthException;
import com.storder.order.user.entity.User;
import com.storder.order.user.entity.UserDetails;
import com.storder.order.user.entity.UserRole;
import com.storder.order.user.repository.UserRepository;
import com.univcert.api.UnivCert;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AuthService {

    @Value("${univcert.api.key}")
	private String apiKey;

    private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;

	public Boolean sendCertificationCode(String email) throws IOException {
		UnivCert.clear(apiKey, email);
		Map<String, Object> response = UnivCert.certify(apiKey, email, "서울과학기술대학교", false);

		if (response.get("success") == Boolean.FALSE) {
			throw new AuthException(SEND_CERTIFICATION_CODE_ERROR);
		}

		return true;
	}

	@Transactional
	public AuthResponseDto.EmailVerification verifyCertificationCode(
		AuthRequestDto.EmailVerification request) throws IOException {
		Map<String, Object> response =
			UnivCert.certifyCode(apiKey, request.getEmail(), "서울과학기술대학교", request.getCode());

		if (response.get("success") == Boolean.FALSE) {
			throw new AuthException(VERIFY_CERTIFICATION_CODE_ERROR);
		}

		User user = User.createEmailVerifiedUser(request.getEmail());
		userRepository.save(user);

		return AuthResponseDto.EmailVerification.of(response);
	}

    @Transactional
	public void signUp(AuthRequestDto.SignUp request) {
		if (!request.isPasswordValid()) {
			throw new AuthException(NOT_VALID_PASSWORD);
		}

		if (request.isNotPasswordCheckValid()) {
			throw new AuthException(NOT_EQUAL_PASSWORD_AND_PASSWORD_CHECK);
		}

		User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
			() -> new AuthException(NOT_EXIST_VERIFIED_EMAIL));
		user.signUp(request);
	}

    public AuthResponseDto.TokenInfo login(AuthRequestDto.Login request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
            () -> new AuthException(NOT_EXIST_LOGIN_EMAIL));

        if (request.notEqualPassword(user.getPassword())) {
            throw new AuthException(NOT_EQUAL_PASSWORD);
        }

        return jwtTokenProvider.generateToken(getAuthentication(user.getEmail(), user.getRole()), user.getName());
    }

	@Transactional
	public void leaving(User user) {
		userRepository.delete(user);
	}

    private Authentication getAuthentication(String email, UserRole role) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new AuthException(NOT_EXIST_EMAIL));
        UserDetails userDetails = new UserDetails(user);

        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }

}
