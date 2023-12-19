package com.dailyon.reviewservice.domain.review.service;

import com.dailyon.reviewservice.IntegrationTestSupport;
import com.dailyon.reviewservice.common.exception.DuplicatedException;
import com.dailyon.reviewservice.domain.review.entity.Review;
import com.dailyon.reviewservice.domain.review.repository.ReviewRepository;
import com.dailyon.reviewservice.domain.review.service.request.ReviewServiceRequest.ReviewCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReviewServiceTest extends IntegrationTestSupport {
  @Autowired ReviewRepository reviewRepository;
  @Autowired ReviewService reviewService;

  @DisplayName("입력 정보를 받아 리뷰를 생성한다.")
  @Test
  void createReview() {
    // given
    ReviewCreateRequest request =
        ReviewCreateRequest.builder()
            .memberId(1L)
            .description("여기설명")
            .imgUrl("testImgUrl")
            .nickname("닉네임")
            .orderDetailNo("orderDetailNo")
            .productId(1L)
            .profile("profile_url")
            .rating(2.5f)
            .build();
    // when
    Review review = reviewService.createReview(request);
    // then
    assertThat(review).isNotNull();
    assertThat(review)
        .extracting(
            "memberId",
            "description",
            "imgUrl",
            "nickname",
            "orderDetailNo",
            "productId",
            "profileImgUrl",
            "rating")
        .containsExactlyInAnyOrder(
            request.getMemberId(),
            request.getDescription(),
            request.getImgUrl(),
            request.getNickname(),
            request.getOrderDetailNo(),
            request.getProductId(),
            request.getProfile(),
            request.getRating());
  }

  @DisplayName("동일 주문 상품에 리뷰를 작성 할 때 예외가 발생한다.")
  @Test
  void createReviewWithDuplicateOrderDetailNo() {
    // given
    ReviewCreateRequest request =
        ReviewCreateRequest.builder()
            .memberId(1L)
            .description("여기설명")
            .imgUrl("testImgUrl")
            .nickname("닉네임")
            .orderDetailNo("orderDetailNo")
            .productId(1L)
            .profile("profile_url")
            .rating(2.5f)
            .build();

    Review review = reviewService.createReview(request);
    // when // then
    assertThatThrownBy(() -> reviewService.createReview(request))
        .isInstanceOf(DuplicatedException.class)
        .hasMessage(DuplicatedException.MESSAGE);
  }
}
