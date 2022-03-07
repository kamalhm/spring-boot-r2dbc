package com.khm.reactivepostgres.controller;

import com.khm.reactivepostgres.dto.CreateTransactionWebRequest;
import com.khm.reactivepostgres.entity.Balance;
import com.khm.reactivepostgres.service.TransactionalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

  private final TransactionalService transactionalService;

  @PostMapping
  @Transactional
  public Mono<Balance> createTransaction(@RequestBody CreateTransactionWebRequest request) {
    return transactionalService.doTransaction(request);
  }
}
