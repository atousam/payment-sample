package org.sample.payment.api.bill;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/3/2024
 */

import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.SneakyThrows;
import org.sample.payment.config.ApplicationProperties;
import org.sample.payment.config.WireMockConfig;
import wiremock.org.apache.commons.io.IOUtils;
import wiremock.org.apache.http.HttpHeaders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BillInquiryApi {
    private static String RESPONSE_SUCCESS_PATH = "body/json/bill/inquiry/successfulRes.json";
    private static String RESPONSE_SUCCESS;

    @SneakyThrows
    public BillInquiryApi() {
        RESPONSE_SUCCESS = IOUtils.toString(BillInquiryApi.class.getClassLoader().getResourceAsStream(RESPONSE_SUCCESS_PATH));
    }

    public void succeed() {
        WireMockConfig.getWireMockConfig().stubFor(WireMock.post(WireMock
                        .urlEqualTo(ApplicationProperties.getProperty("org.sample.payment.service.inquiry.path")))
                .willReturn(ok().withBody(RESPONSE_SUCCESS)
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")));
    }
}
