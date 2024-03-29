package org.sample.payment.dto.bill;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Atousa Mirhosseini
 * @since 29 Jan, 2024
 */
@Getter
@Setter
public class BillInquiryResponseDto {
    private String billId;
    private String payId;
    private Long amount;
    private String address;
}
