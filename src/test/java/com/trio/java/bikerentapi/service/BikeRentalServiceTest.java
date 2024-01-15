package com.trio.java.bikerentapi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.trio.java.bikerentapi.data.Bike;
import com.trio.java.bikerentapi.data.BikeRental;
import com.trio.java.bikerentapi.dto.request.BikeRentalRequest;
import com.trio.java.bikerentapi.exception.BikeNotFoundException;
import com.trio.java.bikerentapi.exception.MaxBikeLoadExceededException;
import com.trio.java.bikerentapi.exception.UnavailableBikeException;
import com.trio.java.bikerentapi.repository.BikeRentalRepository;
import com.trio.java.bikerentapi.repository.BikeRepository;
import com.trio.java.bikerentapi.util.ObjectsFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource(locations = "classpath:test-application.properties")
class BikeRentalServiceTest {

  @MockBean
  private BikeRepository bikeRepository;

  @MockBean
  private BikeRentalRepository bikeRentalRepository;

  @Autowired
  private BikeRentalService bikeRentalService;

  @Test
  void shouldNotRentBikeWhenBikeDetailsNotPresent() {
    val bikeId = 404;
    val rentalDays = 2;
    val riderWeight = 50.00;
    val customerName = "Vinicius Brito";
    val customerEmail = "email@email.com";
    val request =
        ObjectsFactory.createBikeRentalRequest(bikeId, rentalDays, riderWeight, LocalDate.now(),
            customerName, customerEmail);
    when(bikeRepository.getBikeDetails(bikeId)).thenReturn(Optional.empty());
    Assertions.assertThrows(BikeNotFoundException.class, () -> bikeRentalService.rentBike(request));
  }

  @Test
  void shouldNotRentBikeWhenRentalPeriodIsInvalid() {
    val bikeRentalId = 555;
    val bikeId = 308;
    val rentalDays = 2;
    val foundBike = ObjectsFactory.createBike(bikeId, "Some bike", null);
    val startDate = LocalDate.now();
    val riderWeight = 2;
    val customerName = "Vinicius Brito";
    val customerEmail = "email@email.com";
    val bikeRental =
        ObjectsFactory.createBikeRental(bikeRentalId, 0.0, 0.0, 0.0, rentalDays, foundBike,
            startDate, customerName, customerEmail);
    when(bikeRepository.getBikeDetails(bikeId)).thenReturn(Optional.of(foundBike));
    BikeRental existingRental =
        ObjectsFactory.createBikeRental(bikeRentalId, 0.0, 0.0, 0.0, rentalDays, foundBike,
            startDate, customerName, customerEmail);
    when(
        bikeRentalRepository
            .findAllBikeRentalByBikeIdEqualsAndEndDateGreaterThanOrderByStartDateAsc(
            bikeId, startDate)).thenReturn(List.of(existingRental));
    when(bikeRentalRepository.save(any())).thenReturn(bikeRental);
    BikeRentalRequest request =
        ObjectsFactory.createBikeRentalRequest(bikeId, rentalDays, riderWeight, startDate,
            customerName, customerEmail);
    Assertions.assertThrows(UnavailableBikeException.class,
        () -> bikeRentalService.rentBike(request));
  }

  @Test
  void shouldNotRentBikeWhenBikeMaxLoadExceeds() {
    val bikeRentalId = 555;
    val bikeId = 308;
    val rentalDays = 2;
    val foundBike = ObjectsFactory.createBike(bikeId, "Some bike", 30);
    val startDate = LocalDate.now();
    val riderWeight = 50;
    val customerName = "Vinicius Brito";
    val customerEmail = "email@email.com";
    when(bikeRepository.getBikeDetails(bikeId)).thenReturn(Optional.of(foundBike));
    when(
        bikeRentalRepository
            .findAllBikeRentalByBikeIdEqualsAndEndDateGreaterThanOrderByStartDateAsc(
            bikeId, startDate)).thenReturn(List.of());
    BikeRental bikeRental =
        ObjectsFactory.createBikeRental(bikeRentalId, 0.0, 0.0, 0.0, rentalDays, foundBike,
            startDate, customerName, customerEmail);
    when(bikeRentalRepository.save(any())).thenReturn(bikeRental);
    BikeRentalRequest request =
        ObjectsFactory.createBikeRentalRequest(bikeId, rentalDays, riderWeight, startDate,
            customerName, customerEmail);
    Assertions.assertThrows(MaxBikeLoadExceededException.class,
        () -> bikeRentalService.rentBike(request));
  }

  @Test
  void shouldRentBike() {
    val bikeRentalId = 555;
    val bikeId = 308;
    val rentalDays = 2;
    val customerName = "Vinicius Brito";
    val customerEmail = "email@email.com";
    Bike foundBike = ObjectsFactory.createBike(bikeId, "Some bike", 150);
    LocalDate startDate = LocalDate.now();
    val riderWeight = 150;
    BikeRentalRequest request =
        ObjectsFactory.createBikeRentalRequest(bikeId, rentalDays, riderWeight, startDate,
            customerName, customerEmail);
    BikeRental bikeRental =
        ObjectsFactory.createBikeRental(bikeRentalId, 0.0, 0.0, 0.0, rentalDays, foundBike,
            startDate, customerName, customerEmail);
    when(bikeRepository.getBikeDetails(bikeId)).thenReturn(Optional.of(foundBike));
    when(
        bikeRentalRepository
            .findAllBikeRentalByBikeIdEqualsAndEndDateGreaterThanOrderByStartDateAsc(
            bikeId, startDate)).thenReturn(List.of());
    when(bikeRentalRepository.save(any())).thenReturn(bikeRental);
    val result = bikeRentalService.rentBike(request);
    Assertions.assertEquals(bikeRentalId, result.getId());
    Assertions.assertEquals(rentalDays, result.getTotalDays());
    Assertions.assertEquals(bikeId, result.getBike().getId());
  }
}
