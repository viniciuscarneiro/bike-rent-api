package com.trio.java.bikerentapi.dto.response;

import lombok.Builder;
import org.springframework.data.geo.Point;

@Builder(setterPrefix = "with")
public record LocationSummaryResponse(Point point, String description) {
}
