package com.dailyon.reviewservice.domain.review.kafka.event.dto;

import com.dailyon.reviewservice.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
  private String orderDetailNo;
  private Long productId;
  private Long memberId;
  private int point;
  private double ratingAvg;

  public static ReviewDTO of(Review review, double ratingAvg, int point) {
    return ReviewDTO.builder()
        .orderDetailNo(review.getOrderDetailNo())
        .productId(review.getProductId())
        .memberId(review.getMemberId())
        .point(point)
        .ratingAvg(ratingAvg)
        .build();
  }
}
