package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.CurrencyEntity;
import com.my.accountmanager.model.dto.CurrencyThirdParty;
import com.my.accountmanager.repository.CurrencyRepository;
import com.my.accountmanager.service.CurrencyService;
import com.my.accountmanager.utils.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Service
public class CurrencyServiceImpl extends BaseCrudServiceImpl<CurrencyEntity, CurrencyRepository> implements CurrencyService {

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository, RestTemplate restTemplate, CurrencyRepository repository1) {
        super(currencyRepository);
        this.restTemplate =  restTemplate;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public CurrencyThirdParty getRate() {
        CurrencyThirdParty currencyRates = restTemplate
                .getForObject(GlobalConstant.EXCHANGE_API_ADDRESS, CurrencyThirdParty.class); //TODO change to try and get from different server
        return currencyRates;
    }

    @Override
    public Optional<CurrencyEntity> getCurrencyByCode(Integer code) {
        CurrencyEntity currencyEntity = this.currencyRepository.findByCode(code);
        return Optional.of(currencyEntity);
    }
}
