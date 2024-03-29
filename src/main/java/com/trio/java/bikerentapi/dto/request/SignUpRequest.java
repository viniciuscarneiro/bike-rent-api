package com.trio.java.bikerentapi.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class SignUpRequest {
  @NotBlank
  private String name;
  @NotBlank
  @Email
  private String email;
  @NotBlank
  private String password;
}
