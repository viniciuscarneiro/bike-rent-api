package com.trio.java.bikerentapi.e2e;

import static com.trio.java.bikerentapi.util.Constants.FIXED_RATE;
import static com.trio.java.bikerentapi.util.Constants.SIGN_UP_MESSAGE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trio.java.bikerentapi.dto.request.BikeRentalRequest;
import com.trio.java.bikerentapi.dto.request.SignUpRequest;
import com.trio.java.bikerentapi.util.ObjectsFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.val;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test-application.properties")
class IntegrationTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldReturnBikes() throws Exception {
    this.mockMvc.perform(
            get("/api/bikes")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", Matchers.greaterThan(0)));
  }

  @Test
  void shouldReturnBikeDetails() throws Exception {
    int id = 1;

    this.mockMvc.perform(
            get(String.format("/api/bikes/%s", id))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(id)))
        .andExpect(jsonPath("$.name", equalTo("Monahan and Sons")));
  }

  @Test
  void shouldReturnErrorForUnknownBike() throws Exception {
    int id = 404;
    this.mockMvc.perform(
            get(String.format("/api/bikes/%s", id))
        )
        .andExpect(status().isNotFound());
  }

  @Test
  void shouldRentBikeWhenEverythingIsOk() throws Exception {
    val bikeId = 1;
    val bikeRentalId = 1;
    val bike = ObjectsFactory.createBike(bikeId, "Some bike");
    val rentalDays = 5;
    val riderWeight = 70.0;
    val bikeRentalRate = 97;
    val subTotal = bikeRentalRate * rentalDays;
    val serviceFee = subTotal * FIXED_RATE;
    val total = subTotal + serviceFee;
    val customerName = "Vinicius Brito";
    val customerEmail = "email@email.com";
    val bikeRental =
        ObjectsFactory.createBikeRental(bikeRentalId, subTotal, serviceFee, total, rentalDays,
            bike, LocalDate.now(), customerName, customerEmail);

    BikeRentalRequest bikeRentalRequest =
        ObjectsFactory.createBikeRentalRequest(bikeId, rentalDays, riderWeight,
            LocalDate.now(), customerName, customerEmail);
    mockMvc.perform(post("/api/bikes/rent")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(bikeRentalRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(bikeRentalId)))
        .andExpect(jsonPath("$.rentedBike.id", equalTo(bikeId)))
        .andExpect(jsonPath("$.startDate",
            equalTo(LocalDate.now().format(DateTimeFormatter.ISO_DATE))))
        .andExpect(jsonPath("$.endDate",
            equalTo(LocalDate.now().plusDays(rentalDays).format(DateTimeFormatter.ISO_DATE))))
        .andExpect(jsonPath("$.serviceFee", equalTo(bikeRental.getServiceFee())))
        .andExpect(jsonPath("$.subTotal", equalTo(bikeRental.getSubTotal())))
        .andExpect(jsonPath("$.total", equalTo(bikeRental.getTotal())));
  }

  @Test
  void shouldSignUserUpWhenEverythingIsOk() throws Exception {
    String name = "user name";
    String username = "email@email.com";
    String password = "123456";
    SignUpRequest signUpRequest = ObjectsFactory.createSignUpRequest(name, username, password);
    mockMvc.perform(post("/api/user/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(signUpRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userId", notNullValue()))
        .andExpect(jsonPath("$.userId", greaterThan(0)))
        .andExpect(jsonPath("$.name", equalTo(name)))
        .andExpect(jsonPath("$.message", equalTo(SIGN_UP_MESSAGE)))
        .andExpect(jsonPath("$.email", equalTo(username)));
  }
}
