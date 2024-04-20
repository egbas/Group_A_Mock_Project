package com.group_A.MyTodo_App.controller;

import com.group_A.MyTodo_App.dto.AuthResponse;
import com.group_A.MyTodo_App.dto.LoginRequestDto;
import com.group_A.MyTodo_App.dto.LoginResponse;
import com.group_A.MyTodo_App.dto.RegisterRequestDto;
import com.group_A.MyTodo_App.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequestDto registerRequestDto){
        return  ResponseEntity.ok(userService.registerUser(registerRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestDto loginRequestDto){
        return  ResponseEntity.ok(userService.login(loginRequestDto));
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout (HttpServletRequest request, HttpServletResponse response){
        SecurityContextHolder.clearContext();
        new SecurityContextLogoutHandler().logout(request,null,null);
        return ResponseEntity.ok("Logout Successful");
    }
}
