package com.dailyon.reviewservice.domain.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.dailyon.reviewservice.domain.review.entity.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  @Override
  public Double findRatingAvgByProductId(Long productId) {
    return queryFactory
        .select(review.rating.avg())
        .from(review)
        .groupBy(review.productId.eq(productId))
        .fetchOne();
  }
}
