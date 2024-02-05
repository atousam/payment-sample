package org.sample.payment;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/3/2024
 */

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.*;
import org.sample.payment.dto.auth.LoginRequestDto;
import org.sample.payment.dto.auth.LoginResponseDto;
import org.sample.payment.dto.bill.BillInquiryRequestDto;
import org.sample.payment.dto.bill.BillInquiryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=none"})
public class TestPaymentBillInquiry {
    public static String token;

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;
    private static WireMockServer wireMockServer = new WireMockServer(options()
            .port(Integer.parseInt("8090"))
    );

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0-debian");

    @BeforeAll
    static void startBeforeAll() {
        wireMockServer.start();
    }

    @BeforeEach
    void loginToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setUsername("admin");
        loginRequestDto.setPassword("123456");
        HttpEntity<LoginRequestDto> request = new HttpEntity<>(loginRequestDto, headers);
        ResponseEntity<LoginResponseDto> response = restTemplate
                .withBasicAuth("admin", "123456")
                .exchange("http://localhost:" + port + "/auth/login", HttpMethod.POST, request, LoginResponseDto.class);
        synchronized (this) {
            token = response.getBody().getToken();
        }
    }

    @Test
    public void success() {
        makeWireMockStub();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, token);
        BillInquiryRequestDto requestDto = new BillInquiryRequestDto();
        HttpEntity<BillInquiryRequestDto> request = new HttpEntity<>(requestDto, headers);
        requestDto.setBillId("11223344");
        ResponseEntity<BillInquiryResponseDto> response = restTemplate
                .exchange("http://localhost:" + port + "/bill/inquiry", HttpMethod.POST, request, BillInquiryResponseDto.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody().getPayId());
    }

    private void makeWireMockStub() {
        wireMockServer.stubFor(WireMock.post(WireMock
                        .urlEqualTo("/bill/inquiry"))
                .willReturn(ok().withBody("{\n" +
                                "  \"code\": 0,\n" +
                                "  \"billId\": \"12212121\",\n" +
                                "  \"payId\": \"10000\",\n" +
                                "  \"amount\": \"10000\",\n" +
                                "  \"address\": \"تهران، خ آزادی، پلاک 102، طبقه اول\"\n" +
                                "}")
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                ));
    }
}
