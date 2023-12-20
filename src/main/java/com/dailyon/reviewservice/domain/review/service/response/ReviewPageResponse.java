package com.dailyon.reviewservice.domain.review.service.response;

import com.dailyon.reviewservice.domain.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ReviewPageResponse {
  private List<ReviewResponse> reviews;
  private Integer totalPages;
  private Long totalElements;

  private ReviewPageResponse(List<ReviewResponse> reviews, Integer totalPages, Long totalElements) {
    this.reviews = reviews;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
  }

  public static ReviewPageResponse of(Page<Review> page, Long memberId) {
    List<ReviewResponse> reviews =
        page.getContent().stream()
            .map(review -> ReviewResponse.of(review, memberId))
            .collect(Collectors.toUnmodifiableList());
    return new ReviewPageResponse(reviews, page.getTotalPages(), page.getTotalElements());
  }
}
