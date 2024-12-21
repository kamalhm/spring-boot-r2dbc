package dev.kamalhm.reactivepostgres.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

public record Balance(
        @Id Long id,
        @Column("member_id") Long memberId,
        Long balance,
        @CreatedBy @Column("created_by") String createdBy,
        @CreatedDate @Column("created_date") Long createdDate,
        @LastModifiedBy @Column("last_modified_by") String lastModifiedBy,
        @LastModifiedDate @Column("last_modified_date") Long lastModifiedDate
) {
    public Balance(Long memberId, Long balance) {
        this(null, memberId, balance, null, null, null, null);
    }
}

