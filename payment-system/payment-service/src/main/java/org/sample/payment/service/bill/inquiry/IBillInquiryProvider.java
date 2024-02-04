package org.sample.payment.service.bill.inquiry;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/4/2024
 */

import org.sample.payment.dto.bill.BillInquiryResponseDto;

public interface IBillInquiryProvider {
    BillInquiryResponseDto inquiry(BillInquiryRequestData requestData);
}
