package com.group_A.MyTodo_App.service;

import com.group_A.MyTodo_App.dto.AuthResponse;
import com.group_A.MyTodo_App.dto.LoginRequestDto;
import com.group_A.MyTodo_App.dto.LoginResponse;
import com.group_A.MyTodo_App.dto.RegisterRequestDto;

public interface UserService {
  AuthResponse registerUser(RegisterRequestDto registerRequestDto);
  LoginResponse login(LoginRequestDto loginRequestDto);
}
