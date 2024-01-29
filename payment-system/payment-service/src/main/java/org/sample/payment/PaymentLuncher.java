package org.sample.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Atousa Mirhosseini
 * @since 29 Jan, 2024
 */
@EnableFeignClients
@SpringBootApplication
public class PaymentLuncher {

    public static void main(String[] args) {
        SpringApplication.run(PaymentLuncher.class, args);
    }
}