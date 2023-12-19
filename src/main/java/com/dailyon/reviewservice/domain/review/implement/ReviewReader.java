package com.dailyon.reviewservice.domain.review.implement;

import com.dailyon.reviewservice.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewReader {
  private final ReviewRepository reviewRepository;
}
