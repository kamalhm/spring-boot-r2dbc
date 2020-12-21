package com.khm.reactivepostgres;


import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value = "/api/member")
public class MemberController {

  @Autowired
  private MemberRepository memberRepository;

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

  @PutMapping
  public Mono<Member> updateMember(@RequestBody Member member) {
    return memberRepository.findByName(member.getName())
        .flatMap(memberResult -> memberRepository.save(member));
  }

  @DeleteMapping
  public Mono<Void> deleteBook(@RequestBody Member member) {
    return memberRepository.deleteById(member.getId());
  }
}
