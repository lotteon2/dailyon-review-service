package com.dailyon.reviewservice.domain.review.service;

import com.dailyon.reviewservice.domain.review.entity.Review;
import com.dailyon.reviewservice.domain.review.implement.ReviewAppender;
import com.dailyon.reviewservice.domain.review.implement.ReviewReader;
import com.dailyon.reviewservice.domain.review.service.request.ReviewServiceRequest.ReviewCreateRequest;
import com.dailyon.reviewservice.domain.review.service.response.ReviewPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
  private final ReviewAppender reviewAppender;
  private final ReviewReader reviewReader;

  @Transactional
  public Review createReview(ReviewCreateRequest request, String filePath) {
    Review review = request.toEntity(filePath);
    Review savedReview = reviewAppender.append(review);
    return savedReview;
  }

  public Double getRatingAvg(Long productId) {
    return reviewReader.readAvg(productId);
  }

  public ReviewPageResponse getProductReviews(Pageable pageable, Long productId, Long memberId) {
    Page<Review> page = reviewReader.read(pageable, productId);
    return ReviewPageResponse.of(page, memberId);
  }
}
