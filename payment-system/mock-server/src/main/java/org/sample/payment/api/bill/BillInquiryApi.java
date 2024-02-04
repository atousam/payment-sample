package org.sample.payment.api.bill;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/3/2024
 */

import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.SneakyThrows;
import org.sample.payment.config.ApplicationProperties;
import org.sample.payment.config.WireMockConfig;
import wiremock.com.google.common.net.HttpHeaders;
import wiremock.org.apache.commons.io.IOUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BillInquiryApi {
    private static String RESPONSE_SUCCESS_PATH_A = "body/json/bill/inquiry/a/successfulRes.json";
    private static String RESPONSE_SUCCESS_PATH_B = "body/json/bill/inquiry/b/successfulRes.json";
    private static String RESPONSE_ERROR_PATH_A = "body/json/bill/inquiry/a/notFoundBillRes.json";
    private static String RESPONSE_ERROR_PATH_B = "body/json/bill/inquiry/b/notFoundBillRes.json";
    private static String RESPONSE_SUCCESS_A, RESPONSE_SUCCESS_B, RESPONSE_ERROR_A, RESPONSE_ERROR_B;

    @SneakyThrows
    public BillInquiryApi() {
        RESPONSE_SUCCESS_A = IOUtils.toString(BillInquiryApi.class.getClassLoader().getResourceAsStream(RESPONSE_SUCCESS_PATH_A));
        RESPONSE_ERROR_A = IOUtils.toString(BillInquiryApi.class.getClassLoader().getResourceAsStream(RESPONSE_ERROR_PATH_A));
        RESPONSE_SUCCESS_B = IOUtils.toString(BillInquiryApi.class.getClassLoader().getResourceAsStream(RESPONSE_SUCCESS_PATH_B));
        RESPONSE_ERROR_B = IOUtils.toString(BillInquiryApi.class.getClassLoader().getResourceAsStream(RESPONSE_ERROR_PATH_B));
    }

    public void succeed() {
        generateSuccessMock("org.sample.payment.service.inquiry.a.path", RESPONSE_SUCCESS_A);
        generateSuccessMock("org.sample.payment.service.inquiry.b.path", RESPONSE_SUCCESS_B);
    }

    private void generateSuccessMock(String path, String response) {
        WireMockConfig.getWireMockConfig().stubFor(WireMock.post(WireMock
                        .urlEqualTo(ApplicationProperties.getProperty(path)))
                .withRequestBody(matchingJsonPath("$.billId", notMatching("10000")))
                .willReturn(ok().withBody(response)
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")));
    }

    public void notFoundBillRes() {
        generateNotFoundBillRes("org.sample.payment.service.inquiry.a.path", RESPONSE_ERROR_A);
        generateNotFoundBillRes("org.sample.payment.service.inquiry.b.path", RESPONSE_ERROR_B);
    }

    private void generateNotFoundBillRes(String path, String response) {
        WireMockConfig.getWireMockConfig().stubFor(WireMock.post(WireMock
                        .urlEqualTo(ApplicationProperties.getProperty(path)))
                .withRequestBody(matchingJsonPath("$.billId", matching("10000")))
                .willReturn(ok().withBody(response)
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")));
    }
}
