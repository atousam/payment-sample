package org.sample.payment.client.bill.inquiry.providera.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Atousa Mirhosseini
 * @since 02 Feb, 2024
 */
@Getter
@Setter
@AllArgsConstructor
public class ExternalBillInqReqDto {
    private String billId;
}
