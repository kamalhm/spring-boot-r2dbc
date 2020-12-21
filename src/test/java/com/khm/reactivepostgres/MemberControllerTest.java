package com.khm.reactivepostgres;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;


@SpringBootTest
@AutoConfigureWebTestClient
@Slf4j
@ActiveProfiles(profiles = "test")
class MemberControllerTest {

  @Autowired
  private WebTestClient webTestClient;
  @Autowired
  private MemberRepository memberRepository;

  @Value("${spring.r2dbc.url}")
  private String dbUrl;

  private void insertData() {
    initializeDatabase();
    Flux<Member> memberFlux = Flux.just(Member.builder().name("ani").build(),
        Member.builder().name("budi").build(),
        Member.builder().name("cep").build(),
        Member.builder().name("dod").build());
    memberRepository.deleteAll()
        .thenMany(memberFlux)
        .flatMap(memberRepository::save)
        .doOnNext(member -> log.info("inserted {}", member))
        .blockLast();
  }

  private void initializeDatabase() {
    ConnectionFactory connectionFactory = ConnectionFactories.get(dbUrl);
    R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);
    String query = "CREATE TABLE IF NOT EXISTS member (id SERIAL PRIMARY KEY, name TEXT NOT NULL);";
    template.getDatabaseClient().sql(query).fetch().rowsUpdated().block();
  }

  @Test
  void getAll() {
    insertData();
    webTestClient.get()
        .uri("/api/member")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$")
        .isArray()
        .jsonPath("$[0].name")
        .isEqualTo("ani")
        .jsonPath("$[1].name")
        .isEqualTo("budi")
        .jsonPath("$[2].name")
        .isEqualTo("cep")
        .jsonPath("$[3].name")
        .isEqualTo("dod");
  }

  @Test
  void getOne() {
    insertData();
    webTestClient.get()
        .uri("/api/member/ani")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.name")
        .isEqualTo("ani")
        .jsonPath("$.id")
        .isNumber();
  }
}
