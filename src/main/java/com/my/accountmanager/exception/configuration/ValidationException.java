package com.my.accountmanager.exception.configuration;

import org.springframework.context.i18n.LocaleContextHolder;

public class ValidationException extends RuntimeException implements SystemException {

    private final String fieldName;

    public ValidationException(Throwable t, String msg, String fieldName, String... variables) {
        super(SystemException.messageSource.getMessage(msg, variables, LocaleContextHolder.getLocale()), t);
        this.fieldName = fieldName;
    }

    public ValidationException(String msg, String fieldName, String... variables) {
        super(SystemException.messageSource.getMessage(msg, variables, LocaleContextHolder.getLocale()));
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
