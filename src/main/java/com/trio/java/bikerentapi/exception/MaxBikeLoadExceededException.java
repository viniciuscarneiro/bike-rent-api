package com.trio.java.bikerentapi.exception;

import static com.trio.java.bikerentapi.util.Constants.MAX_BIKE_LOAD_EXCEEDED_ERROR_MESSAGE;

import lombok.Getter;


@Getter
public class MaxBikeLoadExceededException extends RuntimeException {
  public MaxBikeLoadExceededException(Integer bikeId, Integer maxLoad) {
    super(MAX_BIKE_LOAD_EXCEEDED_ERROR_MESSAGE.formatted(bikeId, maxLoad));
  }

}
