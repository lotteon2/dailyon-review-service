package com.dailyon.reviewservice.domain.review.service;

import com.dailyon.reviewservice.IntegrationTestSupport;
import com.dailyon.reviewservice.common.exception.DuplicatedException;
import com.dailyon.reviewservice.domain.review.entity.Review;
import com.dailyon.reviewservice.domain.review.repository.ReviewRepository;
import com.dailyon.reviewservice.domain.review.service.request.ReviewServiceRequest.ReviewCreateRequest;
import com.dailyon.reviewservice.domain.review.service.response.ReviewPageResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            .productSize("260")
            .profile("profile_url")
            .rating(2.5f)
            .build();
    // when
    String uuid = UUID.randomUUID().toString();
    Review review = reviewService.createReview(request, uuid);
    // then
    assertThat(review).isNotNull();
    assertThat(review)
        .extracting(
            "memberId",
            "description",
            "nickname",
            "orderDetailNo",
            "productId",
            "profileImgUrl",
            "rating")
        .containsExactlyInAnyOrder(
            request.getMemberId(),
            request.getDescription(),
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
            .productSize("260")
            .profile("profile_url")
            .rating(2.5f)
            .build();

    String uuid = UUID.randomUUID().toString();
    Review review = reviewService.createReview(request, uuid);
    // when // then
    assertThatThrownBy(() -> reviewService.createReview(request, uuid))
        .isInstanceOf(DuplicatedException.class)
        .hasMessage(DuplicatedException.MESSAGE);
  }

  @DisplayName("상품 별 리뷰를 최신순으로 8개씩 조회한다. 내가 쓴 리뷰가 아닌 경우 isWrittenByMe 값은 false이다.")
  @Test
  void getReviewByProductId() {
    // given
    Long memberId = 1L;
    Long productId = 1L;
    List<Review> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(createReview(memberId, productId, "o1" + i, "d1", 1.0f, "n1", "p1"));
    }
    list.add(createReview(2L, productId, "o12d", "d1", 1.0f, "n1", "p1"));
    reviewRepository.saveAll(list);
    Pageable pageable = PageRequest.of(0, 8, Sort.Direction.valueOf("DESC"), "id");

    // when
    ReviewPageResponse productReviews =
        reviewService.getProductReviews(pageable, productId, memberId);
    // then
    assertThat(productReviews.getTotalPages()).isEqualTo(2);
    assertThat(productReviews.getTotalElements()).isEqualTo(list.size());
    assertThat(productReviews.getReviews().get(0).isWrittenByMe()).isFalse();
    assertThat(productReviews.getReviews().get(1).isWrittenByMe()).isTrue();
  }

  private Review createReview(
      Long memberId,
      Long productId,
      String orderDetailNo,
      String description,
      Float rating,
      String nickname,
      String profileImgUrl) {
    return Review.builder()
        .memberId(memberId)
        .productId(productId)
        .productSize("260")
        .orderDetailNo(orderDetailNo)
        .description(description)
        .rating(rating)
        .nickname(nickname)
        .profileImgUrl(profileImgUrl)
        .build();
  }
}
