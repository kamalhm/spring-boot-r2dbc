package com.khm.reactivepostgres;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Balance {
  @Id
  private String id;
  private Long balance;
}
