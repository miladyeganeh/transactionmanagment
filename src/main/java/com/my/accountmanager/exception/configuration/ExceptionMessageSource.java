package com.my.accountmanager.exception.configuration;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
public class ExceptionMessageSource extends ReloadableResourceBundleMessageSource {
    public ExceptionMessageSource() {
        this.setBasename("classpath:messages");
        this.setDefaultEncoding("UTF8");
    }
}
