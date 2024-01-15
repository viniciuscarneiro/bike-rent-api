package com.trio.java.bikerentapi.service.impl;

import static com.trio.java.bikerentapi.util.Constants.FIXED_RATE;
import static java.time.LocalDate.now;

import com.trio.java.bikerentapi.data.Bike;
import com.trio.java.bikerentapi.data.BikeRental;
import com.trio.java.bikerentapi.dto.request.BikeRentalRequest;
import com.trio.java.bikerentapi.dto.response.LocationSummaryResponse;
import com.trio.java.bikerentapi.exception.BikeNotFoundException;
import com.trio.java.bikerentapi.exception.InvalidCustomerDataException;
import com.trio.java.bikerentapi.exception.MaxBikeLoadExceededException;
import com.trio.java.bikerentapi.exception.UnavailableBikeException;
import com.trio.java.bikerentapi.exception.UserNotFoundException;
import com.trio.java.bikerentapi.repository.BikeRentalRepository;
import com.trio.java.bikerentapi.repository.BikeRepository;
import com.trio.java.bikerentapi.repository.UserRepository;
import com.trio.java.bikerentapi.service.BikeLocationService;
import com.trio.java.bikerentapi.service.BikeRentalService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BikeRentalServiceImpl implements BikeRentalService {

  private final UserRepository userRepository;
  private final BikeRepository bikeRepository;
  private final BikeRentalRepository bikeRentalRepository;
  private final BikeLocationService bikeLocationService;

  @Override
  public BikeRental rentBike(BikeRentalRequest bikeRentalRequest) {
    var customerData = getCustomerData(bikeRentalRequest);
    var foundBike = bikeRepository.getBikeDetails(bikeRentalRequest.getBikeId())
        .orElseThrow(BikeNotFoundException::new);
    validate(bikeRentalRequest, foundBike);
    var bikeLocationPoint = bikeLocationService.getBikeLocation(foundBike.getId());
    var rental = buildBikeRental(bikeRentalRequest, foundBike, bikeLocationPoint, customerData);
    return bikeRentalRepository.save(rental);
  }

  private Pair<String, String> getCustomerData(BikeRentalRequest bikeRentalRequest) {
    if (bikeRentalRequest.getUserId() != null) {
      return getUserDataById(bikeRentalRequest.getUserId());
    } else if (StringUtils.isNotBlank(bikeRentalRequest.getEmail())
        && StringUtils.isNotBlank(bikeRentalRequest.getCustomerName())) {
      return Pair.of(bikeRentalRequest.getEmail(), bikeRentalRequest.getCustomerName());
    }
    throw new InvalidCustomerDataException();
  }

  private Pair<String, String> getUserDataById(Integer userId) {
    var foundUser =
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    return Pair.of(foundUser.getUsername(), foundUser.getName());
  }


  private Double calculateServiceFee(Double subTotal) {
    return Math.round(subTotal * FIXED_RATE * 100.0) / 100.0;
  }

  private Double calculateSubTotal(Integer rentalDays, double bikeRate) {
    return rentalDays * bikeRate;
  }

  private BikeRental buildBikeRental(BikeRentalRequest bikeRentalRequest, Bike foundBike,
                                     LocationSummaryResponse bikeLocationPoint,
                                     Pair<String, String> customerData) {
    var subTotal = calculateSubTotal(bikeRentalRequest.getRentalDays(), foundBike.getRate());
    var serviceFee = calculateServiceFee(subTotal);
    var total = subTotal + serviceFee;
    return BikeRental.builder().withBike(foundBike).withTotalDays(bikeRentalRequest.getRentalDays())
        .withStartDate(bikeRentalRequest.getStartDate())
        .withEndDate(buildRentalEndDate(bikeRentalRequest)).withSubTotal(subTotal)
        .withServiceFee(serviceFee).withTotal(total).withPoint(bikeLocationPoint.point())
        .withLocationDescription(bikeLocationPoint.description())
        .withCustomerEmail(customerData.getFirst()).withCustomerName(customerData.getSecond())
        .build();
  }

  private LocalDate buildRentalEndDate(BikeRentalRequest bikeRentalRequest) {
    return bikeRentalRequest.getStartDate().plusDays(bikeRentalRequest.getRentalDays());
  }

  private void validate(BikeRentalRequest bikeRentalRequest, Bike foundBike) {
    validateRentalPeriod(foundBike, bikeRentalRequest);
    validateBikeMaxLoad(foundBike, bikeRentalRequest);
  }

  private void validateBikeMaxLoad(Bike foundBike, BikeRentalRequest bikeRentalRequest) {
    if (foundBike.getMaxLoad() > 0 && bikeRentalRequest.getRiderWeight() > foundBike.getMaxLoad()) {
      throw new MaxBikeLoadExceededException(foundBike.getId(), foundBike.getMaxLoad());
    }
  }

  private void validateRentalPeriod(Bike foundBike, BikeRentalRequest bikeRentalRequest) {
    List<BikeRental> foundBikeRentals =
        bikeRentalRepository
            .findAllBikeRentalByBikeIdEqualsAndEndDateGreaterThanOrderByStartDateAsc(
                foundBike.getId(), now());
    foundBikeRentals.forEach(foundRental -> {
      if (isRentalPeriodInvalid(bikeRentalRequest, foundRental)) {
        throw new UnavailableBikeException(foundBike.getId());
      }
    });
  }

  private boolean isRentalPeriodInvalid(BikeRentalRequest bikeRentalRequest,
                                        BikeRental foundRental) {
    LocalDate requestedStartDate = bikeRentalRequest.getStartDate();
    LocalDate requestedEndDate = requestedStartDate.plusDays(bikeRentalRequest.getRentalDays());
    LocalDate existingStartDate = foundRental.getStartDate();
    LocalDate existingEndDate = existingStartDate.plusDays(foundRental.getTotalDays());
    return (requestedStartDate.isBefore(existingEndDate)
        && requestedEndDate.isAfter(existingStartDate));
  }

}
