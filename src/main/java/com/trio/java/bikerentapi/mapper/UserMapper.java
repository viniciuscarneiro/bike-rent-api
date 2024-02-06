package com.trio.java.bikerentapi.mapper;

import com.trio.java.bikerentapi.data.User;
import com.trio.java.bikerentapi.dto.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {

  public SignUpResponse fromCreatedUser(User user, String message) {
    return SignUpResponse.builder()
        .withUserId(user.getId())
        .withName(user.getName())
        .withMessage(message)
        .withEmail(user.getUsername())
        .build();
  }
}
