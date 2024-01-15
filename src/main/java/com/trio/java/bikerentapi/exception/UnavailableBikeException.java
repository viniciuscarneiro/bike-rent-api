package com.trio.java.bikerentapi.exception;

import static com.trio.java.bikerentapi.util.Constants.UNAVAILABLE_BIKE_ERROR_MESSAGE;

import lombok.Getter;


@Getter
public class UnavailableBikeException extends RuntimeException {

  public UnavailableBikeException(Integer bikeId) {
    super(UNAVAILABLE_BIKE_ERROR_MESSAGE.formatted(bikeId));
  }

}
