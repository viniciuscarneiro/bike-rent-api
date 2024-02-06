package com.trio.java.bikerentapi.exception;

import static com.trio.java.bikerentapi.util.Constants.INVALID_CUSTOMER_DATA_ERROR_MESSAGE;

import lombok.Getter;


@Getter
public class InvalidCustomerDataException extends RuntimeException {
  public InvalidCustomerDataException() {
    super(INVALID_CUSTOMER_DATA_ERROR_MESSAGE);
  }

}
