package com.storder.order.order.entity;

import com.storder.order.global.entity.BaseEntity;
import com.storder.order.menu.entity.Menu;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderMenu extends BaseEntity {

    @Id
    @Column(name = "order_menu_id", length = 255)
    private String orderMenuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "menu_total_price", nullable = false)
    private Integer menuTotalPrice;

    @Column(name = "has_option", nullable = false)
    private Boolean hasOption;
}
