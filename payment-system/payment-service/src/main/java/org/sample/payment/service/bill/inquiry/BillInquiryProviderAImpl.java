package org.sample.payment.service.bill.inquiry;

import lombok.RequiredArgsConstructor;
import org.sample.payment.client.bill.inquiry.providera.BillInquiryProviderAClient;
import org.sample.payment.client.bill.inquiry.providera.dto.ExternalBillInqReqDto;
import org.sample.payment.client.bill.inquiry.providera.dto.ExternalBillInqResDto;
import org.sample.payment.dto.bill.BillInquiryRequestDto;
import org.sample.payment.dto.bill.BillInquiryResponseDto;
import org.springframework.stereotype.Service;

/**
 * @author Atousa Mirhosseini
 * @since 29 Jan, 2024
 */
@RequiredArgsConstructor
@Service
public class BillInquiryProviderAImpl implements IBillInquiryService{
    private final BillInquiryProviderAClient client;

    @Override
    public BillInquiryResponseDto inquiry(BillInquiryRequestDto requestDto) {
        ExternalBillInqResDto externalResDto = client.inquiryBill(new ExternalBillInqReqDto(requestDto.getBillId()));
        BillInquiryResponseDto responseDto = new BillInquiryResponseDto();
        responseDto.setBillId(externalResDto.getBillId());
        responseDto.setPayId(externalResDto.getPayId());
        responseDto.setAmount(externalResDto.getAmount());
        responseDto.setAddress(externalResDto.getAddress());
        return responseDto;
    }
}
