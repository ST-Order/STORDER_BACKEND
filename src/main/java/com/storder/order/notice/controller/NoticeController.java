package com.storder.order.notice.controller;

import com.storder.order.global.dto.ApiResponse;
import com.storder.order.notice.dto.user.NoticeDetailResponse;
import com.storder.order.notice.dto.user.NoticeListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notice Controller", description = "[유저] 공지사항 관련 API")
public class NoticeController {

    @GetMapping
    @Operation(summary = "공지사항 목록 조회", description = "등록된 공지사항 목록을 조회합니다.")
    public ResponseEntity<ApiResponse<NoticeListResponse>> getAllNotifications() {

        // List<NoticeListResponse> noticeList = null;
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/{noticeId}/details")
    @Operation(summary = "공지사항 상세 조회", description = "특정 공지사항의 상세 정보를 조회합니다.")
    public ResponseEntity<ApiResponse<NoticeDetailResponse>> getNotificationDetails(
            @Parameter(description = "공지사항 ID", example = "1") @PathVariable Long noticeId) {

        // NoticeListResponse noticeDetail = null;
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
