package org.sample.payment;

import org.sample.payment.api.bill.BillInquiryApi;

/**
 * @author Atousa Mirhosseini
 * @since 29 Jan, 2024
 */
public class MockServerStarter {
    public static void main(String[] args) {
        BillInquiryApi billInquiryApi = new BillInquiryApi();
        billInquiryApi.succeed();
        billInquiryApi.notFoundBillRes();
    }
}