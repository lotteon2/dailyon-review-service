package com.dailyon.reviewservice.domain.review.api;

import com.dailyon.reviewservice.ControllerTestSupport;
import com.dailyon.reviewservice.domain.review.api.request.ReviewRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReviewApiControllerTest extends ControllerTestSupport {

  @DisplayName("리뷰를 등록한다.")
  @Test
  void createReview() throws Exception {
    // given
    Long memberId = 1L;
    Long productId = 1L;
    String orderDetailNo = "testOrderDetailNo";
    String description = "testdes";
    String imgUrl = "testUrl";
    String nickname = "testNickname";
    Float rating = 1.5f;
    String profileImgUrl = "profile";

    ReviewRequest.ReviewCreateRequest request =
        createRequest(
            productId, orderDetailNo, description, imgUrl, nickname, rating, profileImgUrl);
    // when  // then
    mockMvc
        .perform(
            post("/reviews")
                .header("memberId", memberId)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated());
  }

  @DisplayName("리뷰를 등록 시 상품번호는 필수 이다.")
  @Test
  void createReviewWithNullProductId() throws Exception {
    // given
    Long memberId = 1L;
    Long productId = null;
    String orderDetailNo = "testOrderDetailNo";
    String description = "testdes";
    String imgUrl = "testUrl";
    String nickname = "testNickname";
    Float rating = 1.5f;
    String profileImgUrl = "profile";

    ReviewRequest.ReviewCreateRequest request =
        createRequest(
            productId, orderDetailNo, description, imgUrl, nickname, rating, profileImgUrl);
    // when  // then
    mockMvc
        .perform(
            post("/reviews")
                .header("memberId", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
        .andExpect(jsonPath("$.validation.*").value("상품번호는 필수입니다."));
  }

  @DisplayName("리뷰를 등록 시 주문상품번호는 필수 이다.")
  @Test
  void createReviewWithNullOrderDetailNo() throws Exception {
    // given
    Long memberId = 1L;
    Long productId = 1L;
    String orderDetailNo = null;
    String description = "testdes";
    String imgUrl = "testUrl";
    String nickname = "testNickname";
    Float rating = 1.5f;
    String profileImgUrl = "profile";

    ReviewRequest.ReviewCreateRequest request =
        createRequest(
            productId, orderDetailNo, description, imgUrl, nickname, rating, profileImgUrl);
    // when  // then
    mockMvc
        .perform(
            post("/reviews")
                .header("memberId", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
        .andExpect(jsonPath("$.validation.*").value("주문상품번호는 필수입니다."));
  }

  @DisplayName("리뷰를 등록 시 리뷰내용은 필수 이다.")
  @Test
  void createReviewWithNullDescription() throws Exception {
    // given
    Long memberId = 1L;
    Long productId = 1L;
    String orderDetailNo = "testOrderDetailNo";
    String description = null;
    String imgUrl = "testUrl";
    String nickname = "testNickname";
    Float rating = 1.5f;
    String profileImgUrl = "profile";

    ReviewRequest.ReviewCreateRequest request =
        createRequest(
            productId, orderDetailNo, description, imgUrl, nickname, rating, profileImgUrl);
    // when  // then
    mockMvc
        .perform(
            post("/reviews")
                .header("memberId", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
        .andExpect(jsonPath("$.validation.*").value("리뷰내용은 필수입니다."));
  }

  @DisplayName("리뷰를 등록 시 닉네임은 필수 이다.")
  @Test
  void createReviewWithNullNickname() throws Exception {
    // given
    Long memberId = 1L;
    Long productId = 1L;
    String orderDetailNo = "testOrderDetailNo";
    String description = "testdes";
    String imgUrl = "testUrl";
    String nickname = null;
    Float rating = 1.5f;
    String profileImgUrl = "profile";

    ReviewRequest.ReviewCreateRequest request =
        createRequest(
            productId, orderDetailNo, description, imgUrl, nickname, rating, profileImgUrl);
    // when  // then
    mockMvc
        .perform(
            post("/reviews")
                .header("memberId", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
        .andExpect(jsonPath("$.validation.*").value("닉네임은 필수입니다."));
  }

  @DisplayName("리뷰를 등록 시 별점은 필수 이다.")
  @Test
  void createReviewWithNullRating() throws Exception {
    // given
    Long memberId = 1L;
    Long productId = 1L;
    String orderDetailNo = "testOrderDetailNo";
    String description = "testdes";
    String imgUrl = "testUrl";
    String nickname = "testNickname";
    Float rating = null;
    String profileImgUrl = "profile";

    ReviewRequest.ReviewCreateRequest request =
        createRequest(
            productId, orderDetailNo, description, imgUrl, nickname, rating, profileImgUrl);
    // when  // then
    mockMvc
        .perform(
            post("/reviews")
                .header("memberId", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
        .andExpect(jsonPath("$.validation.*").value("별점은 필수입니다."));
  }

  @DisplayName("리뷰를 등록 시 프로필이미지는 필수 이다.")
  @Test
  void createReviewWithProfileImgUrl() throws Exception {
    // given
    Long memberId = 1L;
    Long productId = 1L;
    String orderDetailNo = "testOrderDetailNo";
    String description = "testdes";
    String imgUrl = "testUrl";
    String nickname = "testNickname";
    Float rating = 1.5f;
    String profileImgUrl = null;

    ReviewRequest.ReviewCreateRequest request =
        createRequest(
            productId, orderDetailNo, description, imgUrl, nickname, rating, profileImgUrl);
    // when  // then
    mockMvc
        .perform(
            post("/reviews")
                .header("memberId", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
        .andExpect(jsonPath("$.validation.*").value("프로필 이미지는 필수입니다."));
  }

  private ReviewRequest.ReviewCreateRequest createRequest(
      Long productId,
      String orderDetailNo,
      String description,
      String imgUrl,
      String nickname,
      Float rating,
      String profileImgUrl) {
    return new ReviewRequest.ReviewCreateRequest(
        productId, orderDetailNo, description, imgUrl, nickname, rating, profileImgUrl);
  }
}
