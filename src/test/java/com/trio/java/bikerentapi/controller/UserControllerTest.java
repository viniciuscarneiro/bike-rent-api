package com.trio.java.bikerentapi.controller;

import static com.trio.java.bikerentapi.util.Constants.ROLE_USER;
import static com.trio.java.bikerentapi.util.Constants.SIGN_UP_MESSAGE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trio.java.bikerentapi.dto.request.SignUpRequest;
import com.trio.java.bikerentapi.exception.UserAlreadyExistsException;
import com.trio.java.bikerentapi.util.ObjectsFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


class UserControllerTest extends AbstractControllerTest {

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldSignUserUpWhenEverythingIsOk() throws Exception {
    String name = "user name";
    String username = "email@email.com";
    String password = "123456";
    Integer id = 99999;
    var createdUser =
        ObjectsFactory.createUser(id, name, username, password, ROLE_USER, Boolean.FALSE);
    when(userService.signup(any())).thenReturn(createdUser);
    SignUpRequest signUpRequest = ObjectsFactory.createSignUpRequest(name, username, password);
    mockMvc.perform(post("/api/user/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(signUpRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userId", equalTo(id)))
        .andExpect(jsonPath("$.name", equalTo(name)))
        .andExpect(jsonPath("$.message", equalTo(SIGN_UP_MESSAGE)))
        .andExpect(jsonPath("$.email", equalTo(username)));
  }

  @Test
  void shouldNotSignUserUpWhenRequiredDataIsMissing() throws Exception {
    SignUpRequest emptySignUpRequest = ObjectsFactory.createEmptySignUpRequest();
    mockMvc.perform(post("/api/user/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(emptySignUpRequest)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].message", equalTo("email must not be blank")))
        .andExpect(jsonPath("$[1].message", equalTo("password must not be blank")))
        .andExpect(jsonPath("$[2].message", equalTo("name must not be blank")));
  }

  @Test
  void shouldNotSignUserUpWhenAlreadyExists() throws Exception {
    String name = "user name";
    String username = "email@email.com";
    String password = "123456";
    when(userService.signup(any())).thenThrow(new UserAlreadyExistsException());
    SignUpRequest signUpRequest = ObjectsFactory.createSignUpRequest(name, username, password);
    mockMvc.perform(post("/api/user/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(signUpRequest)))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.message", equalTo("User with given e-mail already exists.")));
  }
}
