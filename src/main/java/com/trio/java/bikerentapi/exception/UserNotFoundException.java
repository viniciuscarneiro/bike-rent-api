package com.trio.java.bikerentapi.exception;

import static com.trio.java.bikerentapi.util.Constants.USER_NOT_FOUND_ERROR_MESSAGE;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(Integer userId) {
    super(USER_NOT_FOUND_ERROR_MESSAGE.formatted(userId));
  }
}
