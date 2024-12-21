package dev.kamalhm.reactivepostgres;

import dev.kamalhm.reactivepostgres.entity.Member;
import dev.kamalhm.reactivepostgres.repository.MemberRepository;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
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

import static org.hamcrest.Matchers.containsInAnyOrder;


@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles(profiles = "test")
class MemberControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private MemberRepository memberRepository;

    @Value("${spring.r2dbc.url}")
    private String dbUrl;

    @BeforeEach
    public void setup() {
        initializeDatabase();
        insertData();
    }

    private void initializeDatabase() {
        ConnectionFactory connectionFactory = ConnectionFactories.get(dbUrl);
        R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);
        String query = """
                    CREATE TABLE IF NOT EXISTS member
                    (
                        id   BIGINT GENERATED ALWAYS AS IDENTITY,
                        name TEXT NOT NULL
                    );
                """;
        template.getDatabaseClient().sql(query).fetch().rowsUpdated().block();
    }

    private void insertData() {
        Flux<Member> memberFlux = Flux.just(
                new Member("ani"),
                new Member("budi"),
                new Member("cep"),
                new Member("dod"));
        memberRepository.deleteAll()
                .thenMany(memberFlux)
                .flatMap(memberRepository::save)
                .blockLast();
    }

    @Test
    public void getAll() {
        webTestClient.get()
                .uri("/api/member")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[*].name")
                .value(containsInAnyOrder("ani", "budi", "cep", "dod"));
    }


    @Test
    public void getOne() {
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
