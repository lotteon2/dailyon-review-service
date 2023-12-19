package com.dailyon.reviewservice.domain.review.service;

import com.dailyon.reviewservice.domain.review.entity.Review;
import com.dailyon.reviewservice.domain.review.implement.ReviewAppender;
import com.dailyon.reviewservice.domain.review.implement.ReviewReader;
import com.dailyon.reviewservice.domain.review.service.request.ReviewServiceRequest.ReviewCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
  private final ReviewAppender reviewAppender;
  private final ReviewReader reviewReader;

  @Transactional
  public Review createReview(ReviewCreateRequest request) {
    Review review = request.toEntity();
    Review savedReview = reviewAppender.append(review);
    return savedReview;
  }

  public Double getRatingAvg(Long productId) {
    return reviewReader.readAvg(productId);
  }
}
