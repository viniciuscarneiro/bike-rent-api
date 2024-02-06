package com.trio.java.bikerentapi.exception;

import static com.trio.java.bikerentapi.util.Constants.BIKE_NOT_FOUND_ERROR_MESSAGE;

public class BikeNotFoundException extends RuntimeException {
  public BikeNotFoundException() {
    super(BIKE_NOT_FOUND_ERROR_MESSAGE);
  }
}
