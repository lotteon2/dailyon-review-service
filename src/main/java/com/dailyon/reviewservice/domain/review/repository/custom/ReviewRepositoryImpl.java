package com.dailyon.reviewservice.domain.review.repository.custom;

import com.dailyon.reviewservice.domain.review.entity.Review;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.SimpleTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.dailyon.reviewservice.domain.review.entity.QReview.review;
import static org.springframework.data.support.PageableExecutionUtils.getPage;

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

  @Override
  public Page<Review> findByProductId(Pageable pageable, Long productId) {
    List<Long> ids =
        queryFactory
            .select(review.id)
            .from(review)
            .where(review.productId.eq(productId))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(getOrderCondition(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
            .fetch();

    if (CollectionUtils.isEmpty(ids)) {
      return new PageImpl<>(new ArrayList<>(), pageable, 0);
    }

    List<Review> fetch =
        queryFactory
            .selectFrom(review)
            .where(review.id.in(ids))
            .orderBy(getOrderCondition(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
            .fetch();

    return getPage(fetch, pageable, () -> getTotalCount());
  }

  private Long getTotalCount() {
    return queryFactory.select(review.count()).from(review).fetchOne();
  }

  private List<OrderSpecifier> getOrderCondition(Sort sort) {
    List<OrderSpecifier> orders = new ArrayList<>();
    sort.stream()
        .forEach(
            order -> {
              Order direction = order.isAscending() ? Order.ASC : Order.DESC;
              String property = order.getProperty();
              PathBuilder orderByExpression = new PathBuilder(Review.class, "review");
              orders.add(new OrderSpecifier(direction, orderByExpression.get(property)));
            });
    return orders;
  }
}
