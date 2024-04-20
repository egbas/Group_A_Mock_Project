package com.group_A.MyTodo_App.service;

import com.group_A.MyTodo_App.dto.EmailDetails;

public interface EmailSenderService {
  void sendEmailAlert(EmailDetails emailDetails);
}
