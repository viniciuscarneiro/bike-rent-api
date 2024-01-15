package com.trio.java.bikerentapi.service.impl;

import static com.trio.java.bikerentapi.util.Constants.ROLE_USER;
import static com.trio.java.bikerentapi.util.Constants.SIGN_UP_MESSAGE;

import com.trio.java.bikerentapi.data.User;
import com.trio.java.bikerentapi.dto.request.SignUpRequest;
import com.trio.java.bikerentapi.exception.UserAlreadyExistsException;
import com.trio.java.bikerentapi.exception.UserNotFoundException;
import com.trio.java.bikerentapi.repository.UserRepository;
import com.trio.java.bikerentapi.service.NotificationService;
import com.trio.java.bikerentapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final NotificationService notificationService;

  @Override
  public User signup(SignUpRequest signUpRequest) {
    userRepository.findByUsername(signUpRequest.getEmail()).ifPresent(s -> {
      throw new UserAlreadyExistsException();
    });
    var user = User.builder()
        .withUsername(signUpRequest.getEmail())
        .withPassword(signUpRequest.getPassword())
        .withRole(ROLE_USER)
        .withName(signUpRequest.getName())
        .withActive(Boolean.FALSE)
        .build();
    var createdUser = userRepository.save(user);
    notificationService.sendNotification(createdUser.getUsername(), SIGN_UP_MESSAGE);
    return createdUser;
  }

  @Override
  public void verifyAccount(Integer userId) {
    var foundUser =
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    foundUser.setActive(Boolean.TRUE);
    userRepository.save(foundUser);
  }
}
