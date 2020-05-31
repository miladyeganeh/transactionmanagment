package com.my.accountmanager.exception;

import org.springframework.context.i18n.LocaleContextHolder;

public interface SystemException {
    ExceptionMessageSource messageSource = new ExceptionMessageSource();

    default String createMessage(String msg, String... variables){
        return messageSource.getMessage(msg, variables, LocaleContextHolder.getLocale());
    }
}
