package com.khm.reactivepostgres.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Builder
@Data
public class Member {

  @Id
  private Long id;
  private String name;
}
