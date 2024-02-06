package com.trio.java.bikerentapi.service.impl;

import com.trio.java.bikerentapi.data.Bike;
import com.trio.java.bikerentapi.repository.BikeRepository;
import com.trio.java.bikerentapi.service.BikeService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BikeServiceImpl implements BikeService {
  private final BikeRepository bikeRepository;

  public List<Bike> getAllBikes() {
    return bikeRepository.getAllBikes();
  }

  public Optional<Bike> getBikeDetails(int id) {
    return bikeRepository.getBikeDetails(id);
  }
}
