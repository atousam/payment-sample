package org.sample.payment.service.bill.inquiry;

import feign.FeignException;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sample.payment.client.bill.inquiry.providera.BillInquiryProviderAClient;
import org.sample.payment.client.bill.inquiry.providera.dto.ExternalBillInqReqDto;
import org.sample.payment.client.bill.inquiry.providera.dto.ExternalBillInqResDto;
import org.sample.payment.dto.bill.BillInquiryResponseDto;
import org.sample.payment.exception.BusinessException;
import org.sample.payment.exception.NotFoundBusinessException;
import org.sample.payment.message.LocaleMessageResource;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;

/**
 * @author Atousa Mirhosseini
 * @since 29 Jan, 2024
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BillInquiryProviderAImpl implements IBillInquiryProvider {
    private final BillInquiryProviderAClient client;
    private final LocaleMessageResource messageResource;

    @Override
    public BillInquiryResponseDto inquiry(BillInquiryRequestData requestData) {
        try {
            ExternalBillInqResDto externalResDto = client.inquiryBill(new ExternalBillInqReqDto(requestData.getBillInquiryRequestDto().getBillId()));
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
