package com.dailyon.reviewservice.domain.review.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
    indexes = {
      @Index(name = "idx_member_id", columnList = "memberId"),
      @Index(name = "idx_product_id", columnList = "productId"),
      @Index(name = "idx_order_detail_no", columnList = "orderDetailNo", unique = true)
    })
public class Review extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull private Long memberId;
  @NotNull private Long productId;
  @NotNull private String productSize;
  @NotNull private String orderDetailNo;
  @NotNull private String description;
  @NotNull private Float rating;

  private String imgUrl;
  @NotNull private String nickname;
  @NotNull private String profileImgUrl;

  @Column(nullable = false, columnDefinition = "boolean default false")
  private boolean isDeleted;

  @Builder
  private Review(
      Long memberId,
      Long productId,
      String productSize,
      String orderDetailNo,
      String description,
      Float rating,
      String imgUrl,
      String nickname,
      String profileImgUrl) {
    this.memberId = memberId;
    this.productId = productId;
    this.productSize = productSize;
    this.orderDetailNo = orderDetailNo;
    this.description = description;
    this.rating = rating;
    this.imgUrl = imgUrl;
    this.nickname = nickname;
    this.profileImgUrl = profileImgUrl;
  }
}
