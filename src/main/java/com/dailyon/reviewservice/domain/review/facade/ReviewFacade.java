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
    Review review = reviewService.createReview(request.toServiceRequest());
    Double ratingAvg = reviewService.getRatingAvg(review.getProductId());
    String filePath = awsS3Service.createFilePath(review.getImgUrl());
    String preSignedUrl = awsS3Service.getPreSignedUrl(filePath);
    producer.reviewCreated(ReviewDTO.of(review, ratingAvg, 100));
    return preSignedUrl;
  }

  public ReviewPageResponse getProductReviews(Pageable pageable, Long productId, Long memberId) {
    return getProductReviews(pageable, productId, memberId);
  }
}
