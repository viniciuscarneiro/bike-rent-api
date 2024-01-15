package com.trio.java.bikerentapi.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Builder;
import org.springframework.data.geo.Point;

@Builder(setterPrefix = "with")
public record BikeRentalResponse(Integer id, BikeResponse rentedBike, Double serviceFee,
                                 Double subTotal, Double total,
                                 @JsonFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                 @JsonFormat(pattern = "yyyy-MM-dd") LocalDate endDate, Point point,
                                 String locationDescription, String customerName,
                                 String customerEmail) {
}
