package com.dailyon.reviewservice.domain.review.api;

import com.dailyon.reviewservice.common.page.PageRequest;
import com.dailyon.reviewservice.domain.review.api.request.ReviewRequest;
import com.dailyon.reviewservice.domain.review.facade.ReviewFacade;
import com.dailyon.reviewservice.domain.review.service.response.ReviewPageResponse;
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

  // pageRequest가 받는 값 : page, order, sort
  // 값이 없으면 디폴트 값으로 설정함 page = 0, order = RECENT(id) , sort = DESC
  @GetMapping("/{productId}")
  public ResponseEntity<ReviewPageResponse> getProductReviews(
      @RequestHeader(value = "memberId", required = false) Long memberId,
      @PathVariable(name = "productId") Long productId,
      PageRequest pageRequest) {
    return ResponseEntity.ok(
        reviewFacade.getProductReviews(pageRequest.getPageable(), productId, memberId));
  }
}
