package com.dailyon.reviewservice.domain.review.repository;

public interface ReviewRepositoryCustom {
  Double findRatingAvgByProductId(Long productId);
}
