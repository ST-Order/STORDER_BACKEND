package com.storder.order.menu.service;

import static com.storder.order.menu.exception.MenuErrorCode.*;
import static com.storder.order.user.exception.UserErrorCode.*;

import com.storder.order.menu.entity.Menu;
import com.storder.order.menu.entity.Review;
import com.storder.order.menu.exception.MenuException;
import com.storder.order.menu.repository.MenuRepository;
import com.storder.order.menu.repository.ReviewRepository;
import com.storder.order.user.dto.user.ReviewRequestDto;
import com.storder.order.user.entity.User;
import com.storder.order.user.exception.UserException;
import com.storder.order.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public void createReview(Long userId, ReviewRequestDto requestDto) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        Menu menu =
                menuRepository
                        .findById(requestDto.getMenuId())
                        .orElseThrow(() -> new MenuException(MENU_NOT_FOUND));

        Review review =
                Review.builder()
                        .menu(menu)
                        .user(user)
                        .rating(requestDto.getStarPoint().doubleValue()) // 별점을 Double 타입으로 변환
                        .content(requestDto.getContent())
                        .build();

        reviewRepository.save(review);
    }
}
