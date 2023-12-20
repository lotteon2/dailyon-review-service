package com.dailyon.reviewservice.domain.review.service.response;

import com.dailyon.reviewservice.domain.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponse {
  private Long id;
  private Long productId;
  private String description;
  private Float rating;
  private String imgUrl;
  private String nickname;
  private String profileImgUrl;
  private boolean isWrittenByMe;

  @Builder
  private ReviewResponse(
      Long productId,
      String description,
      Float rating,
      String imgUrl,
      String nickname,
      String profileImgUrl,
      boolean isWrittenByMe) {
    this.productId = productId;
    this.description = description;
    this.rating = rating;
    this.imgUrl = imgUrl;
    this.nickname = nickname;
    this.profileImgUrl = profileImgUrl;
    this.isWrittenByMe = isWrittenByMe;
  }

  public static ReviewResponse of(Review review, Long memberId) {
    return ReviewResponse.builder()
        .productId(review.getProductId())
        .description(review.getDescription())
        .rating(review.getRating())
        .imgUrl(review.getImgUrl())
        .nickname(review.getNickname())
        .profileImgUrl(review.getProfileImgUrl())
        .isWrittenByMe((memberId == null || review.getMemberId() != memberId) ? false : true)
        .build();
  }
}
