package com.group_A.MyTodo_App.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDetails {
  private String recipient;
  private String attachment;
  private String subject;
  private String messageBody;
}

