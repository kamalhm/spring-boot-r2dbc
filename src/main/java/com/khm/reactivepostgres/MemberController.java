package com.khm.reactivepostgres;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "/api/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberRepository memberRepository;

  @GetMapping
  public Flux<Member> getAll() {
    return memberRepository.findAll();
  }

  @GetMapping(value = "/{name}")
  public Mono<Member> getOne(@PathVariable String name) {
    return memberRepository.findByName(name);
  }

  @PostMapping
  public Mono<Member> createMember(@RequestBody Member member) {
    return memberRepository.save(member);
  }

  @PostMapping(value = "/{number}")
  public Flux<Member> createMembers(@PathVariable int number) {

    return generateRandomMember(number).subscribeOn(Schedulers.parallel());
  }

  private Flux<Member> generateRandomMember(int number) {
    return Mono.defer(
            () -> Mono.just(Member.builder().name(RandomStringUtils.randomAlphabetic(5)).build()))
        .flatMap(memberRepository::save)
        .repeat(number);
  }

  @PutMapping
  public Mono<Member> updateMember(@RequestBody Member member) {
    return memberRepository
        .findByName(member.getName())
        .flatMap(memberResult -> memberRepository.save(member));
  }

  @DeleteMapping
  public Mono<Void> deleteMember(@RequestBody Member member) {
    return memberRepository.deleteById(member.getId());
  }
}
