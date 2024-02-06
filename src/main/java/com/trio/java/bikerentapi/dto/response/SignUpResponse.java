package com.trio.java.bikerentapi.dto.response;

import lombok.Builder;

@Builder(setterPrefix = "with")
public record SignUpResponse(String message, String name, String email, Integer userId) {
}
