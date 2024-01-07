package com.dailyon.reviewservice.domain.review.facade;

import com.dailyon.reviewservice.common.aws.AwsS3Service;
import com.dailyon.reviewservice.domain.review.entity.Review;
import com.dailyon.reviewservice.domain.review.facade.request.ReviewFacadeRequest.ReviewCreateRequest;
import com.dailyon.reviewservice.domain.review.kafka.event.ReviewEventProducer;
import com.dailyon.reviewservice.domain.review.kafka.event.dto.ReviewDTO;
import com.dailyon.reviewservice.domain.review.service.ReviewService;
import com.dailyon.reviewservice.domain.review.service.response.ReviewPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewFacade {
  private final ReviewService reviewService;
  private final AwsS3Service awsS3Service;
  private final ReviewEventProducer producer;

  public String createReview(ReviewCreateRequest request) {
    String filePath = null;
    String preSignedUrl = null;
    if(request.getImgUrl() != null) {
      filePath = awsS3Service.createFilePath(request.getImgUrl());
      preSignedUrl = awsS3Service.getPreSignedUrl(filePath);
    }
    Review review = reviewService.createReview(request.toServiceRequest(filePath));
    Double ratingAvg = reviewService.getRatingAvg(review.getProductId());
    producer.reviewCreated(ReviewDTO.of(review, ratingAvg, 100));
    return preSignedUrl;
  }

  public ReviewPageResponse getProductReviews(Pageable pageable, Long productId, Long memberId) {
    return reviewService.getProductReviews(pageable, productId, memberId);
  }
}
