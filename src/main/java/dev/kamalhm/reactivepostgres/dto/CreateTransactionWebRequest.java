package dev.kamalhm.reactivepostgres.dto;

import lombok.Data;

@Data
public class CreateTransactionWebRequest {

  private Long from;
  private Long to;
  private Long amount;
}