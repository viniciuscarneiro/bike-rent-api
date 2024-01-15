package com.trio.java.bikerentapi.util;

import com.trio.java.bikerentapi.data.Bike;
import com.trio.java.bikerentapi.data.BikeRental;
import com.trio.java.bikerentapi.data.User;
import com.trio.java.bikerentapi.dto.request.BikeRentalRequest;
import com.trio.java.bikerentapi.dto.request.SignUpRequest;
import java.time.LocalDate;
import java.util.ArrayList;

public class ObjectsFactory {

  public static Bike createBike(int id, String name, Integer maxLoad) {
    return Bike.builder()
        .withId(id)
        .withName(name)
        .withImageUrls(new ArrayList<>())
        .withMaxLoad(maxLoad == null ? 0 : maxLoad)
        .build();
  }

  public static Bike createBike(int id, String name) {
    return createBike(id, name, null);
  }

  public static BikeRentalRequest createBikeRentalRequest(int bikeId, int rentalDays,
                                                          double riderWeight,
                                                          LocalDate startDate, String customerName,
                                                          String customerEmail) {
    BikeRentalRequest request = new BikeRentalRequest();
    request.setBikeId(bikeId);
    request.setRentalDays(rentalDays);
    request.setRiderWeight(riderWeight);
    request.setStartDate(startDate);
    request.setCustomerName(customerName);
    request.setEmail(customerEmail);
    return request;
  }

  public static BikeRental createBikeRental(Integer bikeRentalId, double subTotal,
                                            double serviceFee, double total, int rentalDays,
                                            Bike bike, LocalDate startDate, String customerName,
                                            String customerEmail) {
    return BikeRental.builder()
        .withId(bikeRentalId)
        .withTotalDays(rentalDays)
        .withBike(bike)
        .withStartDate(startDate)
        .withEndDate(startDate.plusDays(rentalDays))
        .withServiceFee(serviceFee)
        .withSubTotal(subTotal)
        .withTotal(total)
        .withCustomerName(customerName)
        .withCustomerEmail(customerEmail)
        .build();
  }

  public static BikeRental createBikeRentalWithoutId(int rentalDays, Bike bike,
                                                     LocalDate startDate) {
    return BikeRental.builder()
        .withServiceFee(100)
        .withTotalDays(rentalDays)
        .withBike(bike)
        .withStartDate(startDate)
        .withEndDate(startDate.plusDays(rentalDays))
        .build();
  }

  public static BikeRentalRequest createEmptyBikeRentalRequest() {
    return new BikeRentalRequest();
  }

  public static User createUser(Integer id, String name, String username, String password,
                                String role, Boolean active) {
    return User.builder()
        .withId(id)
        .withUsername(username)
        .withPassword(password)
        .withRole(role)
        .withName(name)
        .withActive(active)
        .build();
  }

  public static SignUpRequest createSignUpRequest(String name, String username, String password) {
    SignUpRequest request = new SignUpRequest();
    request.setName(name);
    request.setEmail(username);
    request.setPassword(password);
    return request;
  }

  public static SignUpRequest createEmptySignUpRequest() {
    return new SignUpRequest();
  }
}
