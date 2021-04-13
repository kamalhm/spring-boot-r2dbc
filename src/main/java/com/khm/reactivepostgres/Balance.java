package com.khm.reactivepostgres;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Builder
@Data
public class Balance {
  @Id
  private String id;
  @Column("member_id")
  private String memberId;
  private Long balance;
}
