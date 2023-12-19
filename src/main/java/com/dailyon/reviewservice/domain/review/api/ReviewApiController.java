package com.dailyon.reviewservice.domain.review.api;

import com.dailyon.reviewservice.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewApiController {
  private final ReviewService reviewService;
}
