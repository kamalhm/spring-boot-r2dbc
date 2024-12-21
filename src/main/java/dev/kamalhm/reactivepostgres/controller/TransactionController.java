package dev.kamalhm.reactivepostgres.controller;

import dev.kamalhm.reactivepostgres.dto.CreateTransactionWebRequest;
import dev.kamalhm.reactivepostgres.entity.Balance;
import dev.kamalhm.reactivepostgres.service.TransactionalService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionalService transactionalService;

    public TransactionController(TransactionalService transactionalService) {
        this.transactionalService = transactionalService;
    }

    @PostMapping
    @Transactional
    public Mono<Balance> createTransaction(@RequestBody CreateTransactionWebRequest request) {
        return transactionalService.doTransaction(request);
    }
}
