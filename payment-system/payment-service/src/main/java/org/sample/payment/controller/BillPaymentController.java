package org.sample.payment.controller;

import org.sample.payment.dto.bill.BillInquiryRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Atousa Mirhosseini
 * @since 29 Jan, 2024
 */

@RestController
@RequestMapping("/bill")
public class BillPaymentController {

    @PostMapping("/inquiry")
    public void paymentInquiry(@RequestBody BillInquiryRequestDto requestDto) {

    }
}
