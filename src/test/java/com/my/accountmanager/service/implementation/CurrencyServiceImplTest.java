package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.CurrencyEntity;
import com.my.accountmanager.model.dto.CurrencyThirdPartyDTO;
import com.my.accountmanager.repository.CurrencyRepository;
import com.my.accountmanager.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
class CurrencyServiceImplTest {

    @MockBean
    private RestTemplate restTemplate;
    @MockBean
    private CurrencyRepository currencyRepository;
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyServiceImplTest(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Test
    void getCurrency_test() {
        String EXCHANGE_API_ADDRESS = "https://api.exchangeratesapi.io/latest";
        CurrencyThirdPartyDTO mockRates = createMockRates();
        Mockito.when(restTemplate.getForObject(EXCHANGE_API_ADDRESS, CurrencyThirdPartyDTO.class)).thenReturn(mockRates);
        CurrencyThirdPartyDTO rate = currencyService.getRate();
        assertThat(rate.getRates().size(), is(mockRates.getRates().size()));
        assertThat(rate.getBase(), is(mockRates.getBase()));
        assertThat(rate.getRates().get("HKD"), is(mockRates.getRates().get("HKD")));
    }

    @Test
    void getCurrencyByCode_test() {
        CurrencyEntity mockedEntity = new CurrencyEntity();
        mockedEntity.setCode("1");
        mockedEntity.setName("ISK");
        mockedEntity.setId(1L);
        Mockito.when(currencyRepository.findByCode(any())).thenReturn(Optional.of(mockedEntity));
        Optional<CurrencyEntity> obtainedEntity = currencyService.findByCurrencyByCode("1");
        assertThat(obtainedEntity.isEmpty(), is(false));
        assertThat(obtainedEntity.get().getName(), is(mockedEntity.getName()));
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