package com.trio.java.bikerentapi.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
  public static final Double FIXED_RATE = 0.15;
  public static final String ROLE_USER = "USER";

  public static final String UNEXPECTED_ERROR_MESSAGE = "Sorry, an unexpected error occurred.";
  public static final String SIGN_UP_MESSAGE =
      "Thank you for signing up to Rent Bikes, please click here to confirm your account.";
  public static final String USER_NOT_FOUND_ERROR_MESSAGE = "User was not found for ID %s.";
  public static final String USER_ALREADY_EXISTS_ERROR_MESSAGE =
      "User with given e-mail already exists.";
  public static final String BIKE_NOT_FOUND_ERROR_MESSAGE = "Bike was not found.";
  public static final String MAX_BIKE_LOAD_EXCEEDED_ERROR_MESSAGE =
      "Bike with id %s can only supports %s max load.";
  public static final String UNAVAILABLE_BIKE_ERROR_MESSAGE =
      "Bike with id %s is already rented by another user. "
          + "Please choose a different bike or rental period.";
  public static final String INVALID_CUSTOMER_DATA_ERROR_MESSAGE =
      "User id or customer name and email must not be blank.";
}
