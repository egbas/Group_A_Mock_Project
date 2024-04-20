package com.group_A.MyTodo_App.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationInfo {
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;

  private String token;
}

