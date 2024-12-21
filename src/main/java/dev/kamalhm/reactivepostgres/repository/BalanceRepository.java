package dev.kamalhm.reactivepostgres.repository;

import dev.kamalhm.reactivepostgres.entity.Balance;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface BalanceRepository extends R2dbcRepository<Balance, Long> {
    Mono<Balance> findByMemberId(Long memberId);
}
