package com.my.accountmanager.controller;

import com.my.accountmanager.model.dto.CurrencyThirdParty;
import com.my.accountmanager.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<CurrencyThirdParty> getAll() {
        CurrencyThirdParty rates = this.currencyService.getRate();
        return ResponseEntity.status(HttpStatus.OK).body(rates);
    }
}


