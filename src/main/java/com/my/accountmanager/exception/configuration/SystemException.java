package com.my.accountmanager.exception.configuration;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
public interface SystemException {
    ExceptionMessageSource messageSource = new ExceptionMessageSource();

    default String createMessage(String msg, String... variables){
        return messageSource.getMessage(msg, variables, LocaleContextHolder.getLocale());
    }
}
