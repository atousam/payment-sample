package org.sample.payment.config;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/3/2024
 */

import lombok.extern.slf4j.Slf4j;
import wiremock.com.google.common.base.Preconditions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ApplicationProperties {
    public static final String propertiesFile = "application.properties";
    private static final Properties properties = new Properties();

    static {
        InputStream input =
                ApplicationProperties.class.getClassLoader().getResourceAsStream(propertiesFile);
        Preconditions.checkNotNull(input, "Problem in loading file: '" + propertiesFile + "'.");
        try {
            properties.load(input);
        } catch (IOException e) {
            log.error("Exception in loading file: '" + propertiesFile + "'");
        }
    }

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }
}
