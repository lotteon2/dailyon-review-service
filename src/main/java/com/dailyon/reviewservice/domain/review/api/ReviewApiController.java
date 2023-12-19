package com.dailyon.reviewservice.domain.review.api;

import com.dailyon.reviewservice.domain.review.api.request.ReviewRequest;
import com.dailyon.reviewservice.domain.review.facade.ReviewFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewApiController {
  private final ReviewFacade reviewFacade;

  @PostMapping("")
  public ResponseEntity<String> createReview(
      @RequestHeader(value = "memberId") Long memberId,
      @Valid @RequestBody ReviewRequest.ReviewCreateRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(reviewFacade.createReview(request.toFacadeRequest(memberId)));
  }
}
