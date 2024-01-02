package com.dailyon.reviewservice.domain.review.api.request;

import com.dailyon.reviewservice.domain.review.facade.request.ReviewFacadeRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReviewRequest {
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ReviewCreateRequest {
    @NotNull(message = "상품번호는 필수입니다.")
    private Long productId;

    @NotBlank(message = "주문상품번호는 필수입니다.")
    private String orderDetailNo;

    @NotBlank(message = "리뷰내용은 필수입니다.")
    private String description;

    @NotBlank(message = "상품 옵션은 필수 입니다.")
    private String productSize;

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
          .productSize(productSize)
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
