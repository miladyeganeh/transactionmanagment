package com.my.accountmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AccountManager {


    public static void main(String[] args) {
        SpringApplication.run(AccountManager.class, args);
    }
}
