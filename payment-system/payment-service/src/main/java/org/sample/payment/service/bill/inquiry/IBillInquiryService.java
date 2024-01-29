package org.sample.payment.service.bill.inquiry;

import org.sample.payment.dto.bill.BillInquiryResponseDto;

/**
 * @author Atousa Mirhosseini
 * @since 29 Jan, 2024
 */
public interface IBillInquiryService {
    BillInquiryResponseDto inquiry(String billId);
}
