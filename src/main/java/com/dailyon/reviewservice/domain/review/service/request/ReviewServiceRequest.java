package com.dailyon.reviewservice.domain.review.service.request;

import com.dailyon.reviewservice.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewServiceRequest {

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class ReviewCreateRequest {
    private Long memberId;
    private Long productId;
    private String orderDetailNo;
    private String description;
    private Float rating;
    private String imgUrl;
    private String nickname;
    private String profile;

    public Review toEntity() {
      return Review.builder()
          .memberId(memberId)
          .productId(productId)
          .orderDetailNo(orderDetailNo)
          .description(description)
          .rating(rating)
          .imgUrl(imgUrl)
          .nickname(nickname)
          .profileImgUrl(profile)
          .build();
    }
  }
}
