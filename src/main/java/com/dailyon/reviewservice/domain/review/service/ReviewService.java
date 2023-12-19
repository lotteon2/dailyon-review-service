package com.dailyon.reviewservice.domain.review.service;

import com.dailyon.reviewservice.domain.review.implement.ReviewAppender;
import com.dailyon.reviewservice.domain.review.implement.ReviewReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
  private final ReviewAppender reviewAppender;
  private final ReviewReader reviewReader;
}
