package com.dailyon.reviewservice.domain.review.repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.SimpleTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.dailyon.reviewservice.domain.review.entity.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  @Override
  public Double findRatingAvgByProductId(Long productId) {
    NumberExpression<Double> avgRating = review.rating.avg().coalesce(0.0);
    SimpleTemplate<Double> roundedAvgRating =
        Expressions.template(Double.class, "ROUND({0}, 2)", avgRating);
    return queryFactory
        .select(roundedAvgRating)
        .from(review)
        .where(review.productId.eq(productId))
        .groupBy(review.productId)
        .fetchOne();
  }
}
