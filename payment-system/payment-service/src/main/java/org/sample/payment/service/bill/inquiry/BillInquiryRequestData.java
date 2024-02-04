package org.sample.payment.service.bill.inquiry;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/4/2024
 */

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.sample.payment.dto.bill.BillInquiryRequestDto;

@Getter
@Setter
@Builder
public class BillInquiryRequestData {
    private BillInquiryRequestDto billInquiryRequestDto;
    private String clientIp;
}
