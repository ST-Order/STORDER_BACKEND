package com.storder.order.menu.controller;

import com.storder.order.global.dto.ApiResponse;
import com.storder.order.menu.dto.response.MenuListResponse;
import com.storder.order.menu.dto.response.PopularMenuResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/menus")
@Tag(name = "Menu Controller", description = "[유저] 메뉴 관련 API")
public class MenuController {

    @GetMapping("/popularity")
    @Operation(summary = "인기 메뉴 목록 조회", description = "인기 메뉴 목록을 조회합니다.")
    public ResponseEntity<ApiResponse<List<PopularMenuResponse>>> getPopularMenus() {

            //List<PopularMenuResponse> popularMenu = null;
            return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/{storeId}/menus")
    @Operation(summary = "메뉴 목록 조회", description = "선택한 식당의 메뉴 목록을 조회합니다.")
    public ResponseEntity<ApiResponse<MenuListResponse>> getMenus(
            @Parameter(description = "식당 ID", example = "1")
            @PathVariable Long storeId
    ) {

            //MenuListResponse menus = null;
            return ResponseEntity.ok(ApiResponse.success(null));
    }
}
