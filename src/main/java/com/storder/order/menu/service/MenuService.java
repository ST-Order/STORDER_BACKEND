package com.storder.order.menu.service;

import static com.storder.order.menu.exception.MenuErrorCode.*;
import static com.storder.order.store.exception.StoreErrorCode.*;
import static com.storder.order.user.exception.UserErrorCode.*;

import com.storder.order.menu.dto.store.MenuRequestDto;
import com.storder.order.menu.dto.store.MenuResponseDto;
import com.storder.order.menu.entity.Menu;
import com.storder.order.menu.entity.MenuOption;
import com.storder.order.menu.exception.MenuException;
import com.storder.order.menu.repository.MenuOptionRepository;
import com.storder.order.menu.repository.MenuRepository;
import com.storder.order.store.entity.Store;
import com.storder.order.store.exception.StoreException;
import com.storder.order.store.repository.StoreRepository;
import com.storder.order.user.entity.User;
import com.storder.order.user.exception.UserException;
import com.storder.order.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    public void createMenu(Long ownerId, MenuRequestDto requestDto) {
        User owner =
                userRepository
                        .findById(ownerId)
                        .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        Store store =
                storeRepository
                        .findByOwner(owner)
                        .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

        Optional<Menu> existingMenu =
                menuRepository.findByStoreAndMenuName(store, requestDto.getMenuName());
        if (existingMenu.isPresent()) {
            throw new MenuException(DUPLICATE_MENU_NAME);
        }

        Menu newMenu =
                Menu.builder()
                        .store(store)
                        .menuName(requestDto.getMenuName())
                        .menuImage(requestDto.getMenuImage())
                        .description(requestDto.getDescription())
                        .price(requestDto.getPrice())
                        .isSoldout(false) // 기본값 판매 가능 상태
                        .isAvailable(true) // 기본값 판매 가능 상태
                        .isBest(requestDto.isBest())
                        .isPopular(requestDto.isPopular())
                        .orderCount(0) // 기본 주문 수 0
                        .menuRating(0.0) // 기본 평점 0.0
                        .build();

        menuRepository.save(newMenu);

        if (requestDto.getOptions() != null && !requestDto.getOptions().isEmpty()) {
            List<MenuOption> options =
                    requestDto.getOptions().stream()
                            .map(
                                    optionDto ->
                                            MenuOption.builder()
                                                    .menu(newMenu)
                                                    .optionName(optionDto.getOptionName())
                                                    .optionPrice(optionDto.getOptionPrice())
                                                    .optionAvailable(optionDto.isOptionAvailable())
                                                    .build())
                            .collect(Collectors.toList());

            menuOptionRepository.saveAll(options);
        }
    }

    @Transactional
    public void deleteMenu(Long ownerId, Long menuId) {
        User owner =
                userRepository
                        .findById(ownerId)
                        .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        Store store =
                storeRepository
                        .findByOwner(owner)
                        .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

        Menu menu =
                menuRepository
                        .findById(menuId)
                        .orElseThrow(() -> new MenuException(MENU_NOT_FOUND));

        if (!menu.getStore().equals(store)) {
            throw new MenuException(NOT_MENU_OWNER);
        }

        List<MenuOption> options = menuOptionRepository.findByMenu(menu);
        if (!options.isEmpty()) {
            menuOptionRepository.deleteAll(options);
        }

        menuRepository.delete(menu);
    }

    @Transactional
    public void updateMenu(Long ownerId, Long menuId, MenuRequestDto requestDto) {
        User owner =
                userRepository
                        .findById(ownerId)
                        .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        Store store =
                storeRepository
                        .findByOwner(owner)
                        .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

        Menu menu =
                menuRepository
                        .findById(menuId)
                        .orElseThrow(() -> new MenuException(MENU_NOT_FOUND));

        if (!menu.getStore().equals(store)) {
            throw new MenuException(NOT_MENU_OWNER);
        }

        Optional<Menu> existingMenu =
                menuRepository.findByStoreAndMenuName(store, requestDto.getMenuName());
        if (existingMenu.isPresent() && !existingMenu.get().getMenuId().equals(menuId)) {
            throw new MenuException(DUPLICATE_MENU_NAME);
        }

        List<MenuOption> existingOptions = menuOptionRepository.findByMenu(menu);
        if (!existingOptions.isEmpty()) {
            menuOptionRepository.deleteAll(existingOptions);
        }

        if (requestDto.getOptions() != null && !requestDto.getOptions().isEmpty()) {
            List<MenuOption> newOptions =
                    requestDto.getOptions().stream()
                            .map(
                                    optionDto ->
                                            MenuOption.builder()
                                                    .menu(menu)
                                                    .optionName(optionDto.getOptionName())
                                                    .optionPrice(optionDto.getOptionPrice())
                                                    .optionAvailable(optionDto.isOptionAvailable())
                                                    .build())
                            .collect(Collectors.toList());

            menuOptionRepository.saveAll(newOptions);
        }

        menu.updateMenu(
                requestDto.getMenuName(),
                requestDto.getMenuImage(),
                requestDto.getDescription(),
                requestDto.getPrice(),
                requestDto.isBest(),
                requestDto.isPopular());

        menuRepository.save(menu);
    }

    @Transactional(readOnly = true)
    public MenuRequestDto getMenuDetail(Long ownerId, Long menuId) {
        User owner =
                userRepository
                        .findById(ownerId)
                        .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        // 2. 사장님의 가게 조회
        Store store =
                storeRepository
                        .findByOwner(owner)
                        .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

        Menu menu =
                menuRepository
                        .findById(menuId)
                        .orElseThrow(() -> new MenuException(MENU_NOT_FOUND));

        if (!menu.getStore().equals(store)) {
            throw new MenuException(NOT_MENU_OWNER);
        }

        List<MenuRequestDto.OptionDto> options =
                menuOptionRepository.findByMenu(menu).stream()
                        .map(
                                option ->
                                        MenuRequestDto.OptionDto.builder()
                                                .optionName(option.getOptionName())
                                                .optionPrice(option.getOptionPrice())
                                                .optionAvailable(option.getOptionAvailable())
                                                .build())
                        .collect(Collectors.toList());

        return MenuRequestDto.builder()
                .menuName(menu.getMenuName())
                .menuImage(menu.getMenuImage())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .isBest(menu.getIsBest())
                .isPopular(menu.getIsPopular())
                .options(options)
                .build();
    }

    @Transactional
    public void updateSoldOutStatus(
            Long ownerId, Long menuId, MenuRequestDto.SoldOutStatusRequest request) {
        User owner =
                userRepository
                        .findById(ownerId)
                        .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        Store store =
                storeRepository
                        .findByOwner(owner)
                        .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

        Menu menu =
                menuRepository
                        .findById(menuId)
                        .orElseThrow(() -> new MenuException(MENU_NOT_FOUND));

        if (!menu.getStore().equals(store)) {
            throw new MenuException(NOT_MENU_OWNER);
        }

        menu.updateSoldOutStatus(request.isSoldOut());

        menuRepository.save(menu);
    }
}
