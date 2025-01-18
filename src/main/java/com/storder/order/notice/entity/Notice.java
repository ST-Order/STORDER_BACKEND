package com.storder.order.notice.entity;

import com.storder.order.global.entity.BaseEntity;
import com.storder.order.store.entity.Store;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notice_id")
	private Long noticeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, length = 2000)
	private String content;

	@Column
	private String image;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private NoticeCategory category;
}