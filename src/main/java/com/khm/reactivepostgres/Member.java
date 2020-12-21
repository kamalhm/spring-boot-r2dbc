package com.khm.reactivepostgres;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;


@Value
@Builder
public class Member {
  @Id
  private Long id;
  private String name;
}
