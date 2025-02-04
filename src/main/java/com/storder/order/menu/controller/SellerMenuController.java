package com.storder.order.menu.controller;

import com.storder.order.global.annotation.ApiErrorExceptionsExample;
import com.storder.order.global.dto.ApiResponse;
import com.storder.order.menu.docs.seller.SellerGetMenuExceptionDocs;
import com.storder.order.menu.docs.seller.SellerMenuExceptionDocs;
import com.storder.order.menu.docs.seller.SellerPostMenuExceptionDocs;
import com.storder.order.menu.docs.seller.SellerSoldOutStatusExceptionDocs;
import com.storder.order.menu.dto.store.MenuRequestDto;
import com.storder.order.menu.dto.store.MenuResponseDto;
import com.storder.order.menu.dto.store.SoldOutMenuResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sellers/menus")
@Tag(name = "Seller Menu Controller", description = "[사장님] 메뉴 관련 API")
public class SellerMenuController {

    @Operation(summary = "등록된 메뉴 조회", description = "모든 등록된 메뉴를 조회합니다.")
    @ApiErrorExceptionsExample(SellerGetMenuExceptionDocs.class)
    @GetMapping
    public ResponseEntity<ApiResponse<MenuResponseDto>> getAllMenus() {
        List<MenuResponseDto.MenuDto> menus =
                List.of(
                        MenuResponseDto.MenuDto.builder()
                                .menuId(1L)
                                .menuName("삼겹덮밥")
                                .isBest(true)
                                .isPopular(true)
                                .menuImage("https://example.com/menu1.png")
                                .description("맛난 삼겹살과 쌈장으로 만든 덮밥")
                                .price(5000)
                                .isSoldOut(false)
                                .isAvailable(true)
                                .options(
                                        List.of(
                                                MenuResponseDto.MenuDto.OptionDto.builder()
                                                        .optionId(101L)
                                                        .optionName("밥 추가")
                                                        .optionPrice(500)
                                                        .optionAvailable(true)
                                                        .build(),
                                                MenuResponseDto.MenuDto.OptionDto.builder()
                                                        .optionId(102L)
                                                        .optionName("곱빼기")
                                                        .optionPrice(1000)
                                                        .optionAvailable(true)
                                                        .build()))
                                .build());
        return ResponseEntity.ok(
                ApiResponse.success(
                        "메뉴 조회에 성공하였습니다.", MenuResponseDto.builder().menus(menus).build()));
    }

    @Operation(summary = "메뉴 등록", description = "새로운 메뉴를 등록합니다.")
    @ApiErrorExceptionsExample(SellerPostMenuExceptionDocs.class)
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createMenu(
            @RequestBody MenuRequestDto menuRequestDto) {
        return ResponseEntity.ok(ApiResponse.success("메뉴 등록에 성공하였습니다.", null));
    }

    @Operation(summary = "메뉴 삭제", description = "특정 메뉴를 삭제합니다.")
    @ApiErrorExceptionsExample(SellerMenuExceptionDocs.class)
    @DeleteMapping("/{menuId}")
    public ResponseEntity<ApiResponse<Void>> deleteMenu(
            @PathVariable @Parameter(description = "메뉴 ID") Long menuId) {
        return ResponseEntity.ok(ApiResponse.success("메뉴 삭제에 성공하였습니다.", null));
    }

    @Operation(summary = "메뉴 수정", description = "특정 메뉴를 수정합니다.")
    @ApiErrorExceptionsExample(SellerMenuExceptionDocs.class)
    @PutMapping("/{menuId}")
    public ResponseEntity<ApiResponse<Void>> updateMenu(
            @PathVariable @Parameter(description = "메뉴 ID") Long menuId,
            @RequestBody MenuRequestDto menuRequestDto) {
        return ResponseEntity.ok(ApiResponse.success("메뉴 수정에 성공하였습니다.", null));
    }

    @Operation(summary = "품절 상태 변경", description = "특정 메뉴의 품절 상태를 변경합니다.")
    @ApiErrorExceptionsExample(SellerSoldOutStatusExceptionDocs.class)
    @PatchMapping("/{menuId}/status")
    public ResponseEntity<ApiResponse<Void>> updateSoldOutStatus(
            @PathVariable @Parameter(description = "메뉴 ID") Long menuId,
            @RequestBody MenuRequestDto.SoldOutStatusRequest soldOutStatusRequest) {
        return ResponseEntity.ok(ApiResponse.success("메뉴 품절 상태가 변경되었습니다.", null));
    }

    @Operation(summary = "품절 메뉴 조회", description = "품절된 모든 메뉴를 조회합니다.")
    @ApiErrorExceptionsExample(SellerGetMenuExceptionDocs.class)
    @GetMapping("/sold-out")
    public ResponseEntity<ApiResponse<SoldOutMenuResponseDto>> getSoldOutMenus() {
        List<SoldOutMenuResponseDto.MenuDto> menus =
                List.of(
                        SoldOutMenuResponseDto.MenuDto.builder()
                                .menuId(1L)
                                .menuName("삼겹덮밥")
                                .menuImage("https://example.com/menu1.png")
                                .price(5000)
                                .isSoldOut(true)
                                .build(),
                        SoldOutMenuResponseDto.MenuDto.builder()
                                .menuId(2L)
                                .menuName("제육덮밥")
                                .menuImage("https://example.com/menu2.png")
                                .price(5000)
                                .isSoldOut(true)
                                .build());
        return ResponseEntity.ok(
                ApiResponse.success(
                        "품절 메뉴 조회에 성공하였습니다.",
                        SoldOutMenuResponseDto.builder().menus(menus).build()));
    }
}
