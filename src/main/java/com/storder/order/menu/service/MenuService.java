package com.storder.order.menu.service;

import static com.storder.order.store.exception.StoreErrorCode.*;
import static com.storder.order.user.exception.UserErrorCode.*;

import com.storder.order.menu.dto.store.MenuResponseDto;
import com.storder.order.menu.entity.Menu;
import com.storder.order.menu.repository.MenuOptionRepository;
import com.storder.order.menu.repository.MenuRepository;
import com.storder.order.store.entity.Store;
import com.storder.order.store.exception.StoreException;
import com.storder.order.store.repository.StoreRepository;
import com.storder.order.user.entity.User;
import com.storder.order.user.exception.UserException;
import com.storder.order.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuOptionRepository menuOptionRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    /** 사장님이 등록한 모든 메뉴 조회 (메뉴 이름 기준 오름차순 정렬) */
    public MenuResponseDto getAllMenus(Long ownerId) {
        // 1. 사장님 정보 조회
        User owner =
                userRepository
                        .findById(ownerId)
                        .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        // 2. 사장님이 등록한 가게 조회 (User 엔티티가 Store를 참조하지 않으므로 StoreRepository 사용)
        Store store =
                storeRepository
                        .findByOwner(owner)
                        .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

        // 3. 해당 가게의 모든 메뉴 조회 (메뉴 이름 오름차순 정렬)
        List<Menu> menus = menuRepository.findByStoreOrderByMenuNameAsc(store);

        // 4. DTO 변환 및 반환
        List<MenuResponseDto.MenuDto> menuDtoList =
                menus.stream()
                        .map(
                                menu ->
                                        MenuResponseDto.MenuDto.builder()
                                                .menuId(menu.getMenuId())
                                                .menuName(menu.getMenuName())
                                                .isBest(menu.getIsBest())
                                                .isPopular(menu.getIsPopular())
                                                .menuImage(menu.getMenuImage())
                                                .description(menu.getDescription())
                                                .price(menu.getPrice())
                                                .isSoldOut(menu.getIsSoldout())
                                                .isAvailable(menu.getIsAvailable())
                                                .options(
                                                        menuOptionRepository
                                                                .findByMenu(menu)
                                                                .stream()
                                                                .map(
                                                                        option ->
                                                                                MenuResponseDto
                                                                                        .MenuDto
                                                                                        .OptionDto
                                                                                        .builder()
                                                                                        .optionId(
                                                                                                option
                                                                                                        .getOptionId())
                                                                                        .optionName(
                                                                                                option
                                                                                                        .getOptionName())
                                                                                        .optionPrice(
                                                                                                option
                                                                                                        .getOptionPrice())
                                                                                        .optionAvailable(
                                                                                                option
                                                                                                        .getOptionAvailable())
                                                                                        .build())
                                                                .collect(Collectors.toList()))
                                                .build())
                        .collect(Collectors.toList());

        return MenuResponseDto.builder().menus(menuDtoList).build();
    }
}
