package org.sample.payment.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.extern.slf4j.Slf4j;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/3/2024
 */
@Slf4j
public class WireMockConfig {
    private static WireMockServer wireMockServer;

    public static WireMockServer getWireMockConfig() {
        if (wireMockServer == null) {
            synchronized (WireMockConfig.class) {
                if (wireMockServer == null) {
                    wireMockServer = new WireMockServer(options()
                            .port(Integer.parseInt("8090"))
                    );
                    wireMockServer.start();
                    log.info("Mock server started");
                }
            }
        }
        return wireMockServer;
    }
}
