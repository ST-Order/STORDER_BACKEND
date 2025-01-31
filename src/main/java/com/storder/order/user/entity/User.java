package com.storder.order.user.entity;

import static com.storder.order.user.entity.UserRole.*;

import com.storder.order.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "유저")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private UserRole role;

    @Column(name = "order_count", nullable = false)
    private Integer orderCount;

    public static User createEmailVerifiedUser(String email) {
        return User.builder()
            .email(email)
            .role(USER)
            .orderCount(0)
            .build();
    }
}
