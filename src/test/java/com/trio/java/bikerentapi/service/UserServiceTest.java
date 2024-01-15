package com.trio.java.bikerentapi.service;

import static com.trio.java.bikerentapi.util.Constants.ROLE_USER;
import static com.trio.java.bikerentapi.util.Constants.SIGN_UP_MESSAGE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.trio.java.bikerentapi.dto.request.SignUpRequest;
import com.trio.java.bikerentapi.exception.UserAlreadyExistsException;
import com.trio.java.bikerentapi.exception.UserNotFoundException;
import com.trio.java.bikerentapi.repository.UserRepository;
import com.trio.java.bikerentapi.util.ObjectsFactory;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource(locations = "classpath:test-application.properties")
class UserServiceTest {

  @MockBean
  private UserRepository userRepository;

  @MockBean
  private NotificationService notificationService;

  @Autowired
  private UserService userService;

  @Test
  void shouldNotSigningUserUpWhenAlreadyExists() {
    String name = "user name";
    String username = "email@email.com";
    String password = "123456";
    Integer id = 99999;
    var existingUser =
        ObjectsFactory.createUser(id, name, username, password, ROLE_USER, Boolean.FALSE);
    when(userRepository.findByUsername(username)).thenReturn(Optional.of(existingUser));
    SignUpRequest request = new SignUpRequest();
    request.setName(name);
    request.setEmail(username);
    request.setPassword(password);
    Assertions.assertThrows(UserAlreadyExistsException.class,
        () -> userService.signup(request));
    verify(userRepository, times(0)).save(any());
    verify(userRepository).findByUsername(username);
    verify(notificationService, times(0)).sendNotification(any(), any());
  }

  @Test
  void shouldSignUserUp() {
    String name = "user name";
    String username = "email@email.com";
    String password = "123456";
    Integer id = 99999;
    var createdUser =
        ObjectsFactory.createUser(id, name, username, password, ROLE_USER, Boolean.FALSE);
    when(userRepository.save(any())).thenReturn(createdUser);
    when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
    SignUpRequest request = ObjectsFactory.createSignUpRequest(name, username, password);
    var result = userService.signup(request);
    Assertions.assertEquals(createdUser, result);
    verify(userRepository, times(1)).save(any());
    verify(userRepository).findByUsername(username);
    verify(notificationService).sendNotification(username, SIGN_UP_MESSAGE);
  }

  @Test
  void shouldNotVerifyAccountWhenUserIsNotFound() {
    Integer id = 99999;
    when(userRepository.findById(id)).thenReturn(Optional.empty());
    Assertions.assertThrows(UserNotFoundException.class, () -> userService.verifyAccount(id));
    verify(userRepository).findById(id);
    verify(userRepository, times(0)).save(any());
  }

  @Test
  void shouldVerifyAccountWhenUserIsNotFound() {
    String name = "user name";
    String username = "email@email.com";
    String password = "123456";
    Integer id = 99999;
    var createdUser =
        ObjectsFactory.createUser(id, name, username, password, ROLE_USER, Boolean.FALSE);
    when(userRepository.findById(id)).thenReturn(Optional.of(createdUser));
    Assertions.assertDoesNotThrow(() -> userService.verifyAccount(id));
    verify(userRepository).findById(id);
    verify(userRepository, times(1)).save(any());
  }
}
