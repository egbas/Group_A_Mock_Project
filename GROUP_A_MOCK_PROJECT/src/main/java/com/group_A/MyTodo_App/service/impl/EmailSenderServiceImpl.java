package com.group_A.MyTodo_App.service.impl;

import com.group_A.MyTodo_App.dto.EmailDetails;
import com.group_A.MyTodo_App.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {
  private final JavaMailSender javaMailSender;


  @org.springframework.beans.factory.annotation.Value("${spring.mail.username}")
  private String senderEmail;
  @Override
  public void sendEmailAlert(EmailDetails emailDetails) {
    try{
      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
      simpleMailMessage.setFrom(senderEmail);
      simpleMailMessage.setTo(emailDetails.getRecipient());
      simpleMailMessage.setText(emailDetails.getMessageBody());
      simpleMailMessage.setSubject(emailDetails.getSubject());

      javaMailSender.send(simpleMailMessage);
      System.out.println("Mail sent successfully");
    } catch (MailException e) {
      throw new RuntimeException(e);
    }
  }
}
