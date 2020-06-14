package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.CurrencyEntity;
import com.my.accountmanager.model.dto.CurrencyThirdPartyDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.withrel.CurrencyDTO;
import com.my.accountmanager.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CurrencyControllerTest {

    @Autowired
    private CurrencyController currencyController;
    @MockBean
    private CurrencyService currencyService;
    private CurrencyThirdPartyDTO mockRates;

    @BeforeEach
    public void setup() {
        mockRates = createMockRates();
    }

    @Test
    public void getExchangesRate_ifFound() throws Exception {
        when(this.currencyService.getRate()).thenReturn(mockRates);
        ResponseEntity<ResponseDTO<CurrencyThirdPartyDTO>> response = currencyController.getExchangesRate();
        assertThat(response.getStatusCode().value(), is(HttpStatus.OK.value()));
        assertThat(Objects.requireNonNull(response.getBody()).getData().getRates().size(), is(mockRates.getRates().size()));
    }

    @Test
    public void getExchangesRate_ifNotFound() {
        when(this.currencyService.getRate()).thenReturn(null);
        ResponseEntity<ResponseDTO<CurrencyThirdPartyDTO>> response = currencyController.getExchangesRate();
        assertThat(response.getStatusCode().value(), is(HttpStatus.NO_CONTENT.value()));
    }

    @Test
    void getCurrency_ifFound() {
        CurrencyEntity mockedCurrency = new CurrencyEntity();
        mockedCurrency.setId(1L);
        mockedCurrency.setCode("EUR");
        mockedCurrency.setName("EUR");
        when(this.currencyService.findById(any())).thenReturn(Optional.of(mockedCurrency));
        ResponseEntity<ResponseDTO<CurrencyDTO>> response = currencyController.getCurrency(1L);
        assertThat(response.getStatusCode().value(), is(HttpStatus.OK.value()));
        assertThat(Objects.requireNonNull(response.getBody()).getData().getCode(), is(mockedCurrency.getCode()));
    }

    @Test
    void getCurrency_ifNotFound() {
        when(this.currencyService.findById(any())).thenReturn(Optional.empty());
        ResponseEntity<ResponseDTO<CurrencyDTO>> response = currencyController.getCurrency(1L);
        assertThat(response.getStatusCode().value(), is(HttpStatus.NOT_FOUND.value()));
    }

    private CurrencyThirdPartyDTO createMockRates() {
        CurrencyThirdPartyDTO currencyRates;
        Map<String, Double> rates = Map.of(
                "HKD", 8.7809,
                "ISK", 148.9,
                "PHP", 56.457,
                "DKK", 7.4564
        );
        currencyRates = new CurrencyThirdPartyDTO();
        currencyRates.setBase("EUR");
        currencyRates.setDate("2020-06-11");
        currencyRates.setRates(rates);
        return currencyRates;
    }
}