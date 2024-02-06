package com.trio.java.bikerentapi.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public final class BikeRentalRequest {
  @NotNull
  private Integer bikeId;

  @NotNull
  private Integer rentalDays;

  @JsonFormat(pattern = "MM-dd-yyyy")
  @NotNull
  @FutureOrPresent
  private LocalDate startDate;

  @NotNull
  private Double riderWeight;

  private String customerName;

  @Email
  private String email;

  private Integer userId;
}
