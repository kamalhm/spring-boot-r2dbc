package com.khm.reactivepostgres.dto;

import lombok.Data;

@Data
public class CreateTransactionWebRequest {

  private String from;
  private String to;
  private Long amount;
}