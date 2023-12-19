package com.dailyon.reviewservice.common.exception;

import org.springframework.http.HttpStatus;

public class DuplicatedException extends CustomException {

  public static final String MESSAGE = "이미 등록되었습니다.";

  public DuplicatedException() {
    super(MESSAGE);
  }

  @Override
  public HttpStatus getStatusCode() {
    return HttpStatus.BAD_REQUEST;
  }
}
