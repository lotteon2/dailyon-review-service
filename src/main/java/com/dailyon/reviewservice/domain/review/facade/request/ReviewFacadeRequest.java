package com.dailyon.reviewservice.domain.review.facade.request;

import com.dailyon.reviewservice.domain.review.service.request.ReviewServiceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewFacadeRequest {
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class ReviewCreateRequest {
    private Long memberId;
    private Long productId;
    private String productSize;
    private String orderDetailNo;
    private String description;
    private Float rating;
    private String imgUrl;
    private String nickname;
    private String profileImgUrl;

    public ReviewServiceRequest.ReviewCreateRequest toServiceRequest(String filePath) {
      return ReviewServiceRequest.ReviewCreateRequest.builder()
          .memberId(memberId)
          .productId(productId)
          .productSize(productSize)
          .orderDetailNo(orderDetailNo)
          .description(description)
          .rating(rating)
          .imgUrl(filePath)
          .nickname(nickname)
          .profile(profileImgUrl)
          .build();
    }
  }
}
