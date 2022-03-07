package com.khm.reactivepostgres.entity;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

@Builder
@Data
public class Balance {

  @Id
  private UUID id;
  @Column("member_id")
  private String memberId;
  private Long balance;
  @CreatedBy
  @Column("created_by")
  private String createdBy;
  @CreatedDate
  @Column("created_date")
  private Long createdDate;
  @LastModifiedBy
  @Column("last_modified_by")
  private String lastModifiedBy;
  @LastModifiedDate
  @Column("last_modified_date")
  private Long lastModifiedDate;
}
