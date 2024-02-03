package org.sample.payment.client.bill.inquiry.providera.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Atousa Mirhosseini
 * @since 02 Feb, 2024
 */
@Getter
@Setter
public class ExternalBillInqResDto {
    private String billId;
    private String payId;
    private Long amount;
    private String address;
}
