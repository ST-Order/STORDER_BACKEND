package com.storder.order.auth.config.jwt;

import static com.storder.order.auth.exception.AuthErrorCode.*;

import java.nio.file.AccessDeniedException;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.storder.order.auth.dto.response.AuthResponseDto;
import com.storder.order.auth.dto.response.TokenUserInfoDto;
import com.storder.order.auth.exception.AuthErrorCode;
import com.storder.order.auth.exception.AuthException;
import com.storder.order.user.entity.User;
import com.storder.order.user.entity.UserDetails;
import com.storder.order.user.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

	private final Key key;
	private final int ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; //일주일
	private final int REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 21;
	private final UserRepository userRepository;

	@Autowired
	public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, UserRepository userRepository) {
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
		this.userRepository = userRepository;
	}

	public AuthResponseDto.TokenInfo generateToken(Authentication authentication, String name) {
		String accessToken = createToken(authentication, ACCESS_TOKEN_EXPIRATION_TIME);
		String refreshToken = createToken(authentication, REFRESH_TOKEN_EXPIRATION_TIME);
		LocalDateTime accessTokenExpiration = LocalDateTime.now().plusDays(ACCESS_TOKEN_EXPIRATION_TIME);
		LocalDateTime refreshTokenExpiration = LocalDateTime.now().plusDays(REFRESH_TOKEN_EXPIRATION_TIME);

		return AuthResponseDto.TokenInfo.create(name, accessToken, refreshToken, accessTokenExpiration, refreshTokenExpiration);
	}

	private String getAuthorities(Authentication authentication) {
		return authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));
	}

	private String createToken(Authentication authentication, int expirationTime) {
		Date tokenExpiration = new Date(getNowTime() + expirationTime);
		String email = ((UserDetails) authentication.getPrincipal()).getEmail();

		return Jwts.builder()
			.setSubject(email)
			.claim("auth", getAuthorities(authentication))
			.setExpiration(tokenExpiration)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	public long getNowTime() {
		return (new Date()).getTime();
	}

	public Authentication getAuthentication(String accessToken) throws AccessDeniedException {
		Claims claims = parseClaims(accessToken);

		if (claims.get("auth") == null) {
			throw new AccessDeniedException("권한이 없습니다.");
		}

		Collection<? extends GrantedAuthority> authorities =
			Arrays.stream(claims.get("auth").toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		String email = claims.getSubject();
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new AuthException(NOT_EXIST_EMAIL));
		UserDetails userDetails = new UserDetails(user);

		return new UsernamePasswordAuthenticationToken(userDetails, accessToken, authorities);
	}

	public boolean validateToken(String accessToken) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken);
			return true;
		} catch (SecurityException | MalformedJwtException e) {
			throw new MalformedJwtException("유효하지 않은 토큰입니다.");
		} catch (ExpiredJwtException e) {
			throw new JwtException("만료된 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			throw new UnsupportedJwtException("변조된 토큰입니다.");
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("토큰이 존재하지 않습니다.");
		}
	}

	public TokenUserInfoDto extractTokenInfoFromJwt(String accessToken) {
		if (accessToken.startsWith("Bearer ")) {
			String resolvedToken = accessToken.substring(7).trim();
			Claims claims = parseClaims(resolvedToken);
			String[] subjectParts = claims.getSubject().split(",");
			String email = subjectParts[0];
			return TokenUserInfoDto.of(email);
		}
		throw new AuthException(NOT_START_WITH_BEARER);
	}

	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(accessToken)
				.getBody();
		} catch (ExpiredJwtException e) {
			throw new JwtException("만료된 토큰입니다.");
		}
	}

}
