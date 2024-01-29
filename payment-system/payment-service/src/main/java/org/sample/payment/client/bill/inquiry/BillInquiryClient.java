package org.sample.payment.client.bill.inquiry;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Atousa Mirhosseini
 * @since 29 Jan, 2024
 */
@FeignClient(name = "atieh-client", url = "${contract.external.bill.inquiry.providerA.url}")
public interface BillInquiryClient {
}
