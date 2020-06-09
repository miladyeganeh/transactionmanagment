package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.CurrencyEntity;
import com.my.accountmanager.repository.CurrencyRepository;
import com.my.accountmanager.service.CurrencyService;
import com.my.accountmanager.utils.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Service
public class CurrencyServiceImpl extends BaseCrudServiceImpl<CurrencyEntity, CurrencyRepository> implements CurrencyService {

    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository repository, RestTemplate restTemplate) {
        super(repository);
        this.restTemplate =  restTemplate;
    }

    @Override
    public CurrencyThirdParty getRate() {
        CurrencyThirdParty currencyRates = restTemplate
                .getForObject(GlobalConstant.exchangeAPIAddress, CurrencyThirdParty.class); //TODO change to try and get from different server
        return currencyRates;
    }
}
