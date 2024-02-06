package com.trio.java.bikerentapi.exception;

import static com.trio.java.bikerentapi.util.Constants.USER_ALREADY_EXISTS_ERROR_MESSAGE;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException() {
    super(USER_ALREADY_EXISTS_ERROR_MESSAGE);
  }
}
