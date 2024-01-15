package com.trio.java.bikerentapi.service;

import com.trio.java.bikerentapi.dto.response.LocationSummaryResponse;

public interface BikeLocationService {
  LocationSummaryResponse getBikeLocation(int bikeId);
}
