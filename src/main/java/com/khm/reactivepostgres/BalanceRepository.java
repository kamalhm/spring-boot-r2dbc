package com.khm.reactivepostgres;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface BalanceRepository extends R2dbcRepository<Balance, String> {
  Mono<Balance> findByMemberId(String memberId);
}
