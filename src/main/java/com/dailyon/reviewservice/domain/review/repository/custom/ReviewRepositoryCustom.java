package com.dailyon.reviewservice.domain.review.repository.custom;

import com.dailyon.reviewservice.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
  Double findRatingAvgByProductId(Long productId);

  Page<Review> findByProductId(Pageable pageable, Long productId);
}
