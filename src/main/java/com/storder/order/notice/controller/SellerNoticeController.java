package com.storder.order.notice.controller;

import com.storder.order.global.dto.ApiResponse;
import com.storder.order.notice.dto.seller.SellerNotificationRequestDto;
import com.storder.order.notice.dto.seller.SellerNotificationResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notice Controller", description = "[사장님] 공지사항 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sellers/notifications")
public class SellerNoticeController {

    @GetMapping
    @Operation(summary = "전체 공지사항 조회", description = "전체 공지사항을 조회합니다.")
    public ResponseEntity<ApiResponse<List<SellerNotificationResponseDto.Brief>>>
            getAllNotifications() {
        return ResponseEntity.ok(ApiResponse.success("공지사항 목록 조회에 성공하였습니다.", null));
    }

    @GetMapping("/{notificationId}/details")
    @Operation(summary = "공지사항 상세 조회", description = "특정 공지사항을 상세 조회합니다.")
    public ResponseEntity<ApiResponse<SellerNotificationResponseDto.Detail>> getNotificationDetails(
            @PathVariable("notificationId") String notificationId) {
        return ResponseEntity.ok(ApiResponse.success("공지사항 상세 조회에 성공하였습니다.", null));
    }

    @PostMapping
    @Operation(summary = "공지사항 등록", description = "공지사항을 등록합니다.")
    public ResponseEntity<ApiResponse<String>> postNotification(
            @RequestBody SellerNotificationRequestDto.Creation request) {
        return ResponseEntity.ok(ApiResponse.success("공지사항 등록에 성공하였습니다.", null));
    }

    @DeleteMapping("/notificationId")
    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제합니다.")
    public ResponseEntity<ApiResponse<String>> deleteNotification() {
        return ResponseEntity.ok(ApiResponse.success("공지사항 삭제에 성공하였습니다.", null));
    }

    @PutMapping("/notificationId")
    @Operation(summary = "공지사항 수정", description = "공지사항을 수정합니다.")
    public ResponseEntity<ApiResponse<String>> modifyNotification(
            @RequestBody SellerNotificationRequestDto.Creation request) {
        return ResponseEntity.ok(ApiResponse.success("공지사항 수정에 성공하였습니다.", null));
    }
}
