package com.storder.order.menu.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_id")
	private Long optionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", nullable = false)
	private Menu menu;

	@Column(name = "option_name", nullable = false)
	private String optionName;

	@Column(name = "option_price", nullable = false)
	private Integer optionPrice;

	@Column(name = "option_available", nullable = false)
	private Boolean optionAvailable;
}