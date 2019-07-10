package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "httpBinUri=http://localhost:${wiremock.server.port}")
@AutoConfigureWireMock(port = 0)
public class ApplicationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void contextLoads() throws Exception {
        webTestClient.get().uri("/get").exchange().expectStatus().isOk().expectBody().jsonPath("$.headers.Hello").isEqualTo("word");

        webTestClient.get().uri("/delay/3")
                .header("Host", "www.hystrix.com")
                .exchange().expectStatus().isOk()
                .expectBody().consumeWith(entityExchangeResult -> System.out.println(entityExchangeResult.getResponseBody()));
    }
}
