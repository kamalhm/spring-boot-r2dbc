package com.khm.reactivepostgres.repository;

import com.khm.reactivepostgres.entity.Balance;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface BalanceRepository extends R2dbcRepository<Balance, String> {
  Mono<Balance> findByMemberId(String memberId);
}
