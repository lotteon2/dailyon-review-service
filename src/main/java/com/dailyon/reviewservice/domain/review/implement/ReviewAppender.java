package com.dailyon.reviewservice.domain.review.implement;

import com.dailyon.reviewservice.common.exception.DuplicatedException;
import com.dailyon.reviewservice.domain.review.entity.Review;
import com.dailyon.reviewservice.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewAppender {
  private final ReviewRepository reviewRepository;

  public Review append(Review review) {
    try {
      return reviewRepository.save(review);
    } catch (DataIntegrityViolationException exception) {
      throw new DuplicatedException();
    }
  }
}
