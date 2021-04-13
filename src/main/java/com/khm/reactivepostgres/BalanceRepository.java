package com.khm.reactivepostgres;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface BalanceRepository extends R2dbcRepository<Balance, String> {
}
