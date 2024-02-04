package org.sample.payment.service.bill.inquiry;

import feign.FeignException;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sample.payment.client.bill.inquiry.providerb.dto.ExternalBillInqReqDto;
import org.sample.payment.client.bill.inquiry.providerb.dto.ExternalBillInqResDto;
import org.sample.payment.client.bill.inquiry.providerb.BillInquiryProviderBClient;
import org.sample.payment.client.bill.inquiry.providerb.dto.ProviderState;
import org.sample.payment.dto.bill.BillInquiryRequestDto;
import org.sample.payment.dto.bill.BillInquiryResponseDto;
import org.sample.payment.exception.BusinessException;
import org.sample.payment.exception.NotFoundBusinessException;
import org.sample.payment.message.LocaleMessageResource;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/4/2024
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BillInquiryProviderBImpl implements IBillInquiryProvider {
    private final BillInquiryProviderBClient client;
    private final LocaleMessageResource messageResource;

    @Override
    public BillInquiryResponseDto inquiry(BillInquiryRequestData requestData) {
        try {
            ExternalBillInqResDto externalResDto = client.inquiryBill(new ExternalBillInqReqDto(requestData.getBillInquiryRequestDto().getBillId(), requestData.getClientIp()));
            ProviderState state = ProviderState.getState(externalResDto.getState());
            if (state == null) {
                throw new BusinessException("1000", messageResource.getMessage("bill.inquiry.invalid.response"));
            }
            switch (state) {
                case SUCCESS:
                    BillInquiryResponseDto responseDto = new BillInquiryResponseDto();
                    responseDto.setBillId(externalResDto.getBillId());
                    responseDto.setPayId(externalResDto.getPayId());
                    responseDto.setAmount(externalResDto.getAmount());
                    return responseDto;
                case INCORRECT_BILL_ID:
                    throw new NotFoundBusinessException("1", messageResource.getMessage("bill.inquiry.not.found.bill"));
                case ERROR:
                    throw new BusinessException("2", messageResource.getMessage("bill.inquiry.provider.error"));
            }
            throw new BusinessException("1000", messageResource.getMessage("bill.inquiry.invalid.response"));
        } catch (RetryableException retryableException) {
            log.error("Exception in calling provider A, ", retryableException);
            if (retryableException.getCause() instanceof SocketTimeoutException) {
                throw new BusinessException("1001", messageResource.getMessage("bill.inquiry.timeout.provider"));
            } else {
                throw new BusinessException("1002", messageResource.getMessage("bill.inquiry.provider.connection.error"));
            }
        } catch (FeignException feignException) {
            log.error("Exception in calling provider A, ", feignException);
            throw new BusinessException("1002", messageResource.getMessage("bill.inquiry.provider.connection.error"));
        }
    }
}
