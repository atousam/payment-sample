package org.sample.payment.message;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/3/2024
 */

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@RequiredArgsConstructor
@Component
public class LocaleMessageResource {
    private final MessageSource messageSource;

    public String getMessage(String code) {
        Locale locale = new Locale("fa");
        return messageSource.getMessage(code, null, locale);
    }

    public String getMessage(String code, Object... objects) {
        Locale locale = new Locale("fa");
        return messageSource.getMessage(code, objects, locale);
    }
}
