package dev.kamalhm.reactivepostgres.repository;

import dev.kamalhm.reactivepostgres.entity.Member;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface MemberRepository extends R2dbcRepository<Member, Long> {
    Mono<Member> findByName(String name);
}
