package org.sample.payment.service.bill.inquiry;

import lombok.RequiredArgsConstructor;
import org.sample.payment.client.bill.inquiry.providera.BillInquiryProviderAClient;
import org.sample.payment.client.bill.inquiry.providera.dto.ExternalBillInqReqDto;
import org.sample.payment.client.bill.inquiry.providera.dto.ExternalBillInqResDto;
import org.sample.payment.dto.bill.BillInquiryRequestDto;
import org.sample.payment.dto.bill.BillInquiryResponseDto;
import org.sample.payment.exception.BusinessException;
import org.sample.payment.exception.NotFoundBusinessException;
import org.sample.payment.message.LocaleMessageResource;
import org.springframework.stereotype.Service;

/**
 * @author Atousa Mirhosseini
 * @since 29 Jan, 2024
 */
@RequiredArgsConstructor
@Service
public class BillInquiryProviderAImpl implements IBillInquiryService{
    private final BillInquiryProviderAClient client;
    private final LocaleMessageResource messageResource;

    @Override
    public BillInquiryResponseDto inquiry(BillInquiryRequestDto requestDto) {
        ExternalBillInqResDto externalResDto = client.inquiryBill(new ExternalBillInqReqDto(requestDto.getBillId()));
        if (externalResDto.getCode() != null) {
            switch (externalResDto.getCode()) {
                case 0:
                    BillInquiryResponseDto responseDto = new BillInquiryResponseDto();
                    responseDto.setBillId(externalResDto.getBillId());
                    responseDto.setPayId(externalResDto.getPayId());
                    responseDto.setAmount(externalResDto.getAmount());
                    responseDto.setAddress(externalResDto.getAddress());
                    return responseDto;
                case 1:
                    throw new NotFoundBusinessException("1", messageResource.getMessage("bill.inquiry.not.found.bill"));
            }
        }
        throw new BusinessException("1000", messageResource.getMessage("bill.inquiry.invalid.response"));
    }
}
