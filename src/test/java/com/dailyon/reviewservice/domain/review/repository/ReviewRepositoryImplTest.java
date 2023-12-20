package com.dailyon.reviewservice.domain.review.repository;

import com.dailyon.reviewservice.IntegrationTestSupport;
import com.dailyon.reviewservice.domain.review.entity.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewRepositoryImplTest extends IntegrationTestSupport {
  @Autowired ReviewRepository reviewRepository;

  @DisplayName("상품 별 리뷰 별점 평균을 조회한다.")
  @Test
  void getRatingAvgByProductId() {
    // given
    Review review = createReview(1L, 1L, "o1", "d1", 1.0f, "n1", "p1");
    Review review2 = createReview(2L, 1L, "o2", "d1", 1.5f, "n1", "p1");
    List<Review> reviewList = List.of(review, review2);
    reviewRepository.saveAll(reviewList);
    // when
    Double ratingAvgByProductId = reviewRepository.findRatingAvgByProductId(1L);
    // then
    double avg = (review.getRating() + review2.getRating()) / reviewList.size();
    assertThat(ratingAvgByProductId).isEqualTo(avg);
  }

  @DisplayName("상품 별 리뷰를 최신순으로 8개씩 조회한다.")
  @Test
  void getReviewByProductID() {
    // given
    List<Review> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(createReview(1L, 1L, "o1" + i, "d1", 1.0f, "n1", "p1"));
    }
    reviewRepository.saveAll(list);
    Pageable pageable = PageRequest.of(0, 8, Sort.Direction.valueOf("DESC"), "id");
    // when
    Page<Review> page = reviewRepository.findByProductId(pageable, 1L);
    // then
    assertThat(page.getTotalPages()).isEqualTo(2);
    assertThat(page.getTotalElements()).isEqualTo(list.size());
    assertThat(page.getContent().get(0).getId()).isEqualTo(list.get(list.size()-1).getId());
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
        .orderDetailNo(orderDetailNo)
        .description(description)
        .rating(rating)
        .nickname(nickname)
        .profileImgUrl(profileImgUrl)
        .build();
  }
}
