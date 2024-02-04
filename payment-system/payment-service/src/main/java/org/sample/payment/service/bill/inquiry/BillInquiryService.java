package org.sample.payment.service.bill.inquiry;

import lombok.RequiredArgsConstructor;
import org.sample.payment.dto.bill.BillInquiryRequestDto;
import org.sample.payment.dto.bill.BillInquiryResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Atousa Mirhosseini
 * @since 29 Jan, 2024
 */
@RequiredArgsConstructor
@Service
public class BillInquiryService {

    private final BillInquiryProviderAImpl providerA;
    private final BillInquiryProviderBImpl providerB;

    @Value("${contract.external.bill.inquiry.enable}")
    private BillInquiryType inquiryType;

    public BillInquiryResponseDto inquiry(BillInquiryRequestDto requestDto, String ip) {
        return billInquiryProvider().inquiry(BillInquiryRequestData.builder()
                .billInquiryRequestDto(requestDto)
                .clientIp(ip)
                .build()
        );
    }

    private IBillInquiryProvider billInquiryProvider() {
        return switch (inquiryType) {
            case A -> providerA;
            case B -> providerB;
        };
    }
}
