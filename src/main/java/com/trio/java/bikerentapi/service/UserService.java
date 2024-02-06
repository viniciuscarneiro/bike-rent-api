package com.trio.java.bikerentapi.service;

import com.trio.java.bikerentapi.data.User;
import com.trio.java.bikerentapi.dto.request.SignUpRequest;

public interface UserService {
  User signup(SignUpRequest signUpRequest);

  void verifyAccount(Integer userId);
}
