package com.storder.order.menu.entity;

import com.storder.order.global.entity.BaseEntity;
import com.storder.order.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "menu_name", nullable = false, length = 100)
    private String menuName;

    @Column(name = "is_best", nullable = false)
    private Boolean isBest;

    @Column(name = "is_popular", nullable = false)
    private Boolean isPopular;

    @Column(nullable = false)
    private Integer price;

    @Column(name = "is_soldout", nullable = false)
    private Boolean isSoldout;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "order_count", nullable = false)
    private Integer orderCount;

    @Column(length = 500)
    private String description;

    @Column(name = "menu_image")
    private String menuImage;

    @Column(name = "menu_rating", nullable = false)
    private Double menuRating;

    public void updateMenu(
            String menuName,
            String menuImage,
            String description,
            int price,
            boolean isBest,
            boolean isPopular) {
        this.menuName = menuName;
        this.menuImage = menuImage;
        this.description = description;
        this.price = price;
        this.isBest = isBest;
        this.isPopular = isPopular;
    }

    public void updateSoldOutStatus(boolean isSoldOut) {
        this.isSoldout = isSoldOut;
    }
}
