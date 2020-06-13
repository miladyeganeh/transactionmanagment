package com.my.accountmanager.model.dto;

import com.my.accountmanager.domain.entity.CurrencyEntity;

import java.util.Map;


public class CurrencyInfo {
    private CurrencyEntity baseCurrency;
    private Map<String, Double> rates;

    public CurrencyInfo(CurrencyEntity baseCurrency, Map<String, Double> rates) {
        this.baseCurrency = baseCurrency;
        this.rates = rates;
    }

    public CurrencyEntity getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(CurrencyEntity baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public double getExchangeRate(String code) {
        return this.getRates().get(code);
    }
}
