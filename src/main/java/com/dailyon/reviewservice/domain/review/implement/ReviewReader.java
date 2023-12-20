package com.dailyon.reviewservice.domain.review.implement;

import com.dailyon.reviewservice.domain.review.entity.Review;
import com.dailyon.reviewservice.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewReader {
  private final ReviewRepository reviewRepository;

  public Double readAvg(Long productId) {
    return reviewRepository.findRatingAvgByProductId(productId);
  }

  public Page<Review> read(Pageable pageable, Long productId) {
    return reviewRepository.findByProductId(pageable, productId);
  }
}
