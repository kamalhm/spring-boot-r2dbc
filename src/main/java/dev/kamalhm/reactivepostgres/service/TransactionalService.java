package dev.kamalhm.reactivepostgres.service;

import dev.kamalhm.reactivepostgres.dto.CreateTransactionWebRequest;
import dev.kamalhm.reactivepostgres.entity.Balance;
import dev.kamalhm.reactivepostgres.repository.BalanceRepository;

import java.util.Random;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RequiredArgsConstructor
@Service
@Slf4j
public class TransactionalService {

    private final BalanceRepository balanceRepository;

    @Transactional
    public Mono<Balance> doTransaction(CreateTransactionWebRequest request) {
        Long amount = request.getAmount();

        return Mono.zip(balanceRepository.findByMemberId(request.getFrom()),
                        balanceRepository.findByMemberId(request.getTo()))
                .flatMap(balanceTuple -> executeTransaction(balanceTuple, amount));
    }

    private Mono<Balance> executeTransaction(Tuple2<Balance, Balance> balanceTuple, Long amount) {
        Balance fromBalance = balanceTuple.getT1();
        Balance toBalance = balanceTuple.getT2();
        return deductBalance(fromBalance, amount).flatMap(
                balance -> increaseBalance(toBalance, amount));
    }

    private Mono<Balance> increaseBalance(Balance toBalance, Long amount) {
        return Mono.fromSupplier(() -> new Random().nextDouble())
                .flatMap(randomValue -> increaseBalance(toBalance, amount, randomValue));
    }

    private Mono<Balance> increaseBalance(Balance toBalance, Long amount, Double randomValue) {
        log.info("random value {}", randomValue);
        if (randomValue < 0.5) {
            return Mono.error(new RuntimeException("randomized error"));
        }
        Balance updatedBalance = new Balance(
                toBalance.id(),
                toBalance.memberId(),
                toBalance.balance() + amount, // Update balance
                toBalance.createdBy(),
                toBalance.createdDate(),
                toBalance.lastModifiedBy(),
                toBalance.lastModifiedDate()
        );
        return balanceRepository.save(updatedBalance);

    }

    private Mono<Balance> deductBalance(Balance fromBalance, Long amount) {
        Balance updatedBalance = new Balance(
                fromBalance.id(),
                fromBalance.memberId(),
                fromBalance.balance() - amount, // Update balance
                fromBalance.createdBy(),
                fromBalance.createdDate(),
                fromBalance.lastModifiedBy(),
                fromBalance.lastModifiedDate()
        );
        return balanceRepository.save(updatedBalance);
    }


}
