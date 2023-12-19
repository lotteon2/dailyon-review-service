package com.dailyon.reviewservice;

import com.dailyon.reviewservice.domain.review.api.ReviewApiController;
import com.dailyon.reviewservice.domain.review.facade.ReviewFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {ReviewApiController.class})
public class ControllerTestSupport {

  @Autowired protected MockMvc mockMvc;
  @Autowired protected ObjectMapper objectMapper;
  @MockBean protected ReviewFacade reviewFacade;
}
