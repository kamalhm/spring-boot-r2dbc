package dev.kamalhm.reactivepostgres.dto;

import lombok.Data;

@Data
public class CreateBalanceWebRequest {

    private Long memberId;
    private Long balance;
}
