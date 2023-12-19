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
  private Long memberId;
  private int point;
  private float rating;

  public static ReviewDTO of(Review review, int point) {
    return ReviewDTO.builder()
        .orderDetailNo(review.getOrderDetailNo())
        .memberId(review.getMemberId())
        .point(point)
        .rating(review.getRating())
        .build();
  }
}
