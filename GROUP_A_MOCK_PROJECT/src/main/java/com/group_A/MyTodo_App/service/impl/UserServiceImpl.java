package com.group_A.MyTodo_App.service.impl;


import com.group_A.MyTodo_App.config.JwtService;
import com.group_A.MyTodo_App.dto.*;
import com.group_A.MyTodo_App.entity.User;
import com.group_A.MyTodo_App.repository.UserRepository;
import com.group_A.MyTodo_App.service.EmailSenderService;
import com.group_A.MyTodo_App.service.UserService;
import com.group_A.MyTodo_App.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder encoder;
  private  final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final EmailSenderService emailSenderService;

  public AuthResponse registerUser(RegisterRequestDto registerRequestDto){
    User userEntity = User.builder()
            .firstName(registerRequestDto.getFirstName())
            .lastName(registerRequestDto.getLastName())
            .email(registerRequestDto.getEmail())
            .phoneNumber(registerRequestDto.getPhoneNumber())
            .password(encoder.encode(registerRequestDto.getPassword()))
            .build();

    User savedUser = userRepository.save(userEntity);
    // send email alert
    EmailDetails emailDetails = EmailDetails.builder()
            .recipient(savedUser.getEmail())
            .subject("ACCOUNT CREATION")
            .messageBody("Congratulation Your account has been successfully created")
            .build();
    emailSenderService.sendEmailAlert(emailDetails);


    var jwtToken = jwtService.generateToken(userEntity);

    return AuthResponse.builder()
            .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE)
            .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
            .registrationInfo(RegistrationInfo.builder()
                    .firstName(userEntity.getFirstName())
                    .lastName(userEntity.getLastName())
                    .email(userEntity.getEmail())
                    .phoneNumber(userEntity.getPhoneNumber())
                    .token(jwtToken)
                    .build())
            .build();
  }

  @Override
  public LoginResponse login(LoginRequestDto loginRequestDto) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequestDto.getEmail(),
                    loginRequestDto.getPassword()
            )
    );
    User userEntity = userRepository.findByEmail(loginRequestDto.getEmail())
            .orElseThrow();
    var jwtToken = jwtService.generateToken(userEntity);
    return LoginResponse
            .builder()
            .responseCode(AccountUtils.LOGIN_SUCCESS_CODE)
            .responseMessage(AccountUtils.LOGIN_SUCCESS_MESSAGE)
            .loginInfo(LoginInfo.builder()
                    .email(userEntity.getEmail())
                    .token(jwtToken)
                    .build())
            .build();
  }
}
