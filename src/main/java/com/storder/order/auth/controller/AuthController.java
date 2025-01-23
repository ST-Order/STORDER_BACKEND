package com.storder.order.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.storder.order.global.dto.ApiResponse;
import com.storder.order.auth.dto.AuthRequestDto;
import com.storder.order.auth.dto.AuthResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Auth Controller", description = "[유저] 로그인 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@PostMapping("/register")
	@Operation(summary = "회원가입", description = "회원가입을 합니다.")
	public ResponseEntity<ApiResponse<String>> signUp(@RequestBody AuthRequestDto.SignUp request) {
		return ResponseEntity.ok(ApiResponse.success("회원가입 요청에 성공하였습니다.", null));
	}

	@GetMapping("/duplication-check")
	@Operation(summary = "이메일 중복 체크", description = "해당 이메일이 이미 가입되어 있는지 확인합니다.")
	public ResponseEntity<ApiResponse<String>> duplicationCheck(@Parameter(description = "이메일", required = true) @RequestParam String email) {
		return ResponseEntity.ok(ApiResponse.success("가입 가능한 이메일입니다.", null));
	}

	@PostMapping("/certification")
	@Operation(summary = "이메일 인증 요청", description = "학교 이메일로 인증 요청을 메일을 보냅니다.")
	public ResponseEntity<ApiResponse<Boolean>> certifyUniversity(@RequestBody AuthRequestDto.EmailCertification request) {
		return ResponseEntity.ok(ApiResponse.success("인증번호 발송에 성공하였습니다.", Boolean.TRUE));
	}

	@PostMapping("/verification")
	@Operation(summary = "이메일 인증 코드 확인", description = "이메일로 보낸 인증 코드를 확인합니다.")
	public ResponseEntity<ApiResponse<AuthResponseDto.EmailVerification>> verifyUniversity(@RequestBody AuthRequestDto.EmailVerification request) {
		return ResponseEntity.ok(ApiResponse.success("인증에 성공하였습니다.", null));
	}

	@PostMapping("/login")
	@Operation(summary = "로그인", description = "로그인을 진행합니다.")
	public ResponseEntity<ApiResponse<AuthResponseDto.TokenInfo>> login(@RequestBody AuthRequestDto.Login request) {
		return ResponseEntity.ok(ApiResponse.success("로그인에 성공하였습니다.", null));
	}

	@DeleteMapping
	@Operation(summary = "회원 탈퇴", description = "앱을 탈퇴합니다.")
	public ResponseEntity<ApiResponse<String>> leaving() {
		return ResponseEntity.ok(ApiResponse.success("회원 탈퇴에 성공하였습니다.", null));
	}

}
