package org.sample.payment.client.bill.inquiry.providera;

import org.sample.payment.client.bill.inquiry.providera.dto.ExternalBillInqReqDto;
import org.sample.payment.client.bill.inquiry.providera.dto.ExternalBillInqResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Atousa Mirhosseini
 * @since 29 Jan, 2024
 */
@FeignClient(name = "providerA-client", url = "${contract.external.bill.inquiry.providerA.url}")
public interface BillInquiryProviderAClient {
    @PostMapping(path = "/inquiry")
    ExternalBillInqResDto inquiryBill(@RequestBody ExternalBillInqReqDto externalBillInqReqDto);
}
