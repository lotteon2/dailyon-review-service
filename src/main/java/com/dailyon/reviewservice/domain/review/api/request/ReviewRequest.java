package com.dailyon.reviewservice.domain.review.api.request;

import com.dailyon.reviewservice.domain.review.facade.request.ReviewFacadeRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReviewRequest {
  public static class ReviewCreateRequest {
    @NotNull(message = "상품번호는 필수입니다.")
    private Long productId;

    @NotBlank(message = "주문상품번호는 필수입니다.")
    private String orderDetailNo;

    @NotBlank(message = "리뷰내용은 필수입니다.")
    private String description;

    private String imgUrl;

    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;

    @NotNull(message = "별점은 필수입니다.")
    private Float rating;

    @NotBlank(message = "프로필 이미지는 필수입니다.")
    private String profileImgUrl;

    public ReviewFacadeRequest.ReviewCreateRequest toFacadeRequest(Long memberId) {
      return ReviewFacadeRequest.ReviewCreateRequest.builder()
          .memberId(memberId)
          .productId(productId)
          .orderDetailNo(orderDetailNo)
          .description(description)
          .rating(rating)
          .imgUrl(imgUrl)
          .nickname(nickname)
          .profileImgUrl(profileImgUrl)
          .build();
    }
  }
}
