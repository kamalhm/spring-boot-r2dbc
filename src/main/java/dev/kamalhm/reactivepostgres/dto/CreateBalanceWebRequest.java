package dev.kamalhm.reactivepostgres.dto;

import lombok.Data;

@Data
public class CreateBalanceWebRequest {

  private String memberId;
  private Long balance;
}
