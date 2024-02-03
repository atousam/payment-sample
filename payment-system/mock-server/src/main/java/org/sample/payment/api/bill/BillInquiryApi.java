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
    private static String RESPONSE_SUCCESS_PATH = "body/json/bill/inquiry/successfulRes.json";
    private static String RESPONSE_ERROR_PATH = "body/json/bill/inquiry/notFoundBillRes.json";
    private static String RESPONSE_SUCCESS, RESPONSE_ERROR;

    @SneakyThrows
    public BillInquiryApi() {
        RESPONSE_SUCCESS = IOUtils.toString(BillInquiryApi.class.getClassLoader().getResourceAsStream(RESPONSE_SUCCESS_PATH));
        RESPONSE_ERROR = IOUtils.toString(BillInquiryApi.class.getClassLoader().getResourceAsStream(RESPONSE_ERROR_PATH));
    }

    public void succeed() {
        WireMockConfig.getWireMockConfig().stubFor(WireMock.post(WireMock
                        .urlEqualTo(ApplicationProperties.getProperty("org.sample.payment.service.inquiry.path")))
                .withRequestBody(matchingJsonPath("$.billId", notMatching("10000")))
                .willReturn(ok().withBody(RESPONSE_SUCCESS)
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")));
    }

    public void notFoundBillRes() {
        WireMockConfig.getWireMockConfig().stubFor(WireMock.post(WireMock
                        .urlEqualTo(ApplicationProperties.getProperty("org.sample.payment.service.inquiry.path")))
                .withRequestBody(matchingJsonPath("$.billId", matching("10000")))
                .willReturn(ok().withBody(RESPONSE_ERROR)
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")));
    }
}
