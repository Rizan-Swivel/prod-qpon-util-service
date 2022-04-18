package com.swivel.util.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * Build messages according to the language
 */
@Component
public class Translator {

    private final ResourceBundleMessageSource messageSource;

    @Autowired
    public Translator(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Return the message based on the language
     *
     * @param msgCode msgCode
     * @return message
     */
    public String toLocale(String msgCode) {
        var locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, null, locale);
    }

}