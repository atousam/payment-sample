package org.sample.payment;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/3/2024
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=none"})
public class TestPaymentBillInquiry {
    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(
            "mysql:8.0-debian"
    );

    @Test
    public void success() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        BillInquiryRequestDto requestDto = new BillInquiryRequestDto();
        HttpEntity<BillInquiryRequestDto> request = new HttpEntity<>(requestDto, headers);
        requestDto.setBillId("11223344");
        ResponseEntity<BillInquiryResponseDto> response = restTemplate
                .withBasicAuth("admin", "123456")
                .exchange("http://localhost:" + port + "/bill/inquiry", HttpMethod.POST, request, BillInquiryResponseDto.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody().getPayId());
    }
}
