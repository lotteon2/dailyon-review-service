package com.dailyon.reviewservice.domain.review.service.response;

import com.dailyon.reviewservice.domain.review.entity.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewResponse {
  private Long id;
  private Long productId;
  private String productSize;
  private String description;
  private Float rating;
  private String imgUrl;
  private String nickname;
  private String profileImgUrl;
  private boolean isWrittenByMe;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "Asia/Seoul")
  private LocalDateTime createdAt;

  @Builder
  private ReviewResponse(
      Long id,
      Long productId,
      String productSize,
      String description,
      Float rating,
      String imgUrl,
      String nickname,
      String profileImgUrl,
      boolean isWrittenByMe,
      LocalDateTime createdAt) {
    this.id = id;
    this.productId = productId;
    this.productSize = productSize;
    this.description = description;
    this.rating = rating;
    this.imgUrl = imgUrl;
    this.nickname = nickname;
    this.profileImgUrl = profileImgUrl;
    this.isWrittenByMe = isWrittenByMe;
    this.createdAt = createdAt;
  }

  public static ReviewResponse of(Review review, Long memberId) {
    return ReviewResponse.builder()
        .id(review.getId())
        .productId(review.getProductId())
        .productSize(review.getProductSize())
        .description(review.getDescription())
        .rating(review.getRating())
        .imgUrl(review.getImgUrl())
        .nickname(review.getNickname())
        .profileImgUrl(review.getProfileImgUrl())
        .isWrittenByMe((memberId == null || review.getMemberId() != memberId) ? false : true)
        .createdAt(review.getCreatedAt())
        .build();
  }
}
