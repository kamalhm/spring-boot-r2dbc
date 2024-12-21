package dev.kamalhm.reactivepostgres.controller;

import dev.kamalhm.reactivepostgres.dto.CreateBalanceWebRequest;
import dev.kamalhm.reactivepostgres.entity.Balance;
import dev.kamalhm.reactivepostgres.repository.BalanceRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/balance")
public class BalanceController {

    private final BalanceRepository balanceRepository;

    public BalanceController(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @PostMapping
    public Mono<Balance> createBalance(@RequestBody CreateBalanceWebRequest request) {
        final Balance balance = new Balance(request.memberId(), request.balance());
        return balanceRepository.save(balance);
    }

}
