package com.dailyon.reviewservice.domain.review.kafka.event;

import com.dailyon.reviewservice.domain.review.kafka.event.dto.ReviewDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewEventProducer {
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;

  public void reviewCreated(ReviewDTO reviewDTO) {
    log.info("review-create -> orderDetailNo {} ", reviewDTO.getOrderDetailNo());
    try {
      kafkaTemplate.send("create-review", objectMapper.writeValueAsString(reviewDTO));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}
