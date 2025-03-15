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

    public MenuResponseDto getAllMenus(Long ownerId) {
        User owner =
                userRepository
                        .findById(ownerId)
                        .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        Store store =
                storeRepository
                        .findById(owner.getUserId())
                        .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

        List<Menu> menus = menuRepository.findByStore(store);

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
