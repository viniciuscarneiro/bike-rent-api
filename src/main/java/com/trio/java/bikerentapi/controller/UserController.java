package com.trio.java.bikerentapi.controller;

import static com.trio.java.bikerentapi.util.Constants.SIGN_UP_MESSAGE;

import com.trio.java.bikerentapi.dto.request.SignUpRequest;
import com.trio.java.bikerentapi.dto.response.SignUpResponse;
import com.trio.java.bikerentapi.mapper.UserMapper;
import com.trio.java.bikerentapi.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @PostMapping(value = "/signup")
  public ResponseEntity<SignUpResponse> signup(
      @Valid @RequestBody SignUpRequest signUpRequest) {
    return ResponseEntity.ok()
        .body(userMapper.fromCreatedUser(userService.signup(signUpRequest), SIGN_UP_MESSAGE));
  }

  @PutMapping(value = "/verify/{userId}")
  public ResponseEntity<?> verify(@PathVariable Integer userId) {
    userService.verifyAccount(userId);
    return ResponseEntity.ok().build();
  }
}
