package com.trio.java.bikerentapi.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.trio.java.bikerentapi.util.ObjectsFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


@WebMvcTest
class BikeControllerTest extends AbstractControllerTest {
  @Test
  void shouldReturnEmptyListIfNoBikesPresent() throws Exception {
    when(bikeService.getAllBikes()).thenReturn(new ArrayList<>());

    this.mockMvc.perform(
            get("/api/bikes")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  void shouldReturnListWithBikes() throws Exception {
    when(bikeService.getAllBikes()).thenReturn(Arrays.asList(
        ObjectsFactory.createBike(1, "Bike1"),
        ObjectsFactory.createBike(2, "Bike2")
    ));

    this.mockMvc.perform(
            get("/api/bikes")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", equalTo(1)))
        .andExpect(jsonPath("$[0].name", equalTo("Bike1")))
        .andExpect(jsonPath("$[1].id", equalTo(2)))
        .andExpect(jsonPath("$[1].name", equalTo("Bike2")));
  }

  @Test
  void shouldReturnBikeDetailsIfPresent() throws Exception {
    int id = 308;

    when(bikeService.getBikeDetails(id))
        .thenReturn(Optional.of(ObjectsFactory.createBike(id, "Some bike")));

    this.mockMvc.perform(
            get(String.format("/api/bikes/%s", id))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(id)))
        .andExpect(jsonPath("$.name", equalTo("Some bike")));
  }

  @Test
  void shouldReturnErrorIfBikeNotFound() throws Exception {
    int id = 404;

    when(bikeService.getBikeDetails(id)).thenReturn(Optional.empty());

    this.mockMvc.perform(
            get(String.format("/api/bikes/%s", id))
        )
        .andExpect(status().isNotFound());
  }
}
