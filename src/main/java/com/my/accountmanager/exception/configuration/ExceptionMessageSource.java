package com.my.accountmanager.exception.configuration;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class ExceptionMessageSource extends ReloadableResourceBundleMessageSource {
    public ExceptionMessageSource() {
        this.setBasename("classpath:messages");
        this.setDefaultEncoding("UTF8");
    }
}
