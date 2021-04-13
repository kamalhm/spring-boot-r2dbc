package com.khm.reactivepostgres;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/balance")
@RequiredArgsConstructor
@Slf4j
public class BalanceController {

  private final BalanceRepository balanceRepository;

  @PostMapping
  public Mono<Balance> createBalance(@RequestBody Balance balance) {
    return balanceRepository.save(balance);
  }

}
