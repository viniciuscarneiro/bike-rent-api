package com.trio.java.bikerentapi.mapper;

import com.trio.java.bikerentapi.data.BikeRental;
import com.trio.java.bikerentapi.dto.response.BikeRentalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BikeRentalMapper {

  private final BikeMapper bikeMapper;

  public BikeRentalResponse fromBikeRental(BikeRental bikeRental) {
    return BikeRentalResponse
        .builder()
        .withId(bikeRental.getId())
        .withRentedBike(bikeMapper.fromBike(bikeRental.getBike()))
        .withStartDate(bikeRental.getStartDate())
        .withEndDate(bikeRental.getEndDate())
        .withServiceFee(bikeRental.getServiceFee())
        .withSubTotal(bikeRental.getSubTotal())
        .withTotal(bikeRental.getTotal())
        .withPoint(bikeRental.getPoint())
        .withLocationDescription(bikeRental.getLocationDescription())
        .withCustomerEmail(bikeRental.getCustomerEmail())
        .withCustomerName(bikeRental.getCustomerName())
        .build();
  }
}
