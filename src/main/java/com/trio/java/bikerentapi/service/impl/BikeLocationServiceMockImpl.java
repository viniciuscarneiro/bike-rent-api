package com.trio.java.bikerentapi.service.impl;

import com.trio.java.bikerentapi.dto.response.LocationSummaryResponse;
import com.trio.java.bikerentapi.service.BikeLocationService;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

/**
 * Mock implementation of the BikeLocationService for testing or development purposes.
 */
@Component
public class BikeLocationServiceMockImpl implements BikeLocationService {

  /**
   * Retrieves mock location data for a given bike ID.
   *
   * @param bikeId The ID of the bike.
   * @return LocationSummaryResponse with mock location data.
   */
  @Override
  public LocationSummaryResponse getBikeLocation(int bikeId) {
    return LocationSummaryResponse.builder()
        .withPoint(new Point(-73.9712, 40.7831))
        .withDescription("Manhattan")
        .build();
  }
}
