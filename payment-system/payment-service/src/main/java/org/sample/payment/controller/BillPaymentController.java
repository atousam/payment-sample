package org.sample.payment.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.sample.payment.dto.bill.BillInquiryRequestDto;
import org.sample.payment.dto.bill.BillInquiryResponseDto;
import org.sample.payment.service.bill.inquiry.BillInquiryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Atousa Mirhosseini
 * @since 29 Jan, 2024
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/bill")
public class BillPaymentController {
    private final BillInquiryService billInquiryService;

    @PostMapping("/inquiry")
    public BillInquiryResponseDto billInquiry(@RequestBody BillInquiryRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return billInquiryService.inquiry(requestDto, httpServletRequest.getRemoteAddr());
    }
}
