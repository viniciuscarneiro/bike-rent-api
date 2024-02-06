package com.trio.java.bikerentapi.data;

import com.trio.java.bikerentapi.util.PointConverter;
import java.time.LocalDate;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.geo.Point;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Entity
@Table(name = "bike_rentals")
public class BikeRental {

  @Id
  @GeneratedValue
  private int id;
  @ManyToOne(optional = false)
  private Bike bike;
  private int totalDays;
  private LocalDate startDate;
  private LocalDate endDate;
  private double subTotal;
  private double serviceFee;
  private double total;
  @Convert(converter = PointConverter.class)
  private Point point;
  private String locationDescription;
  private String customerName;
  private String customerEmail;
}
