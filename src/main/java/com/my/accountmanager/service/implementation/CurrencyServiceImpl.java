package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.CurrencyEntity;
import com.my.accountmanager.model.dto.CurrencyInfo;
import com.my.accountmanager.model.dto.CurrencyThirdPartyDTO;
import com.my.accountmanager.repository.CurrencyRepository;
import com.my.accountmanager.service.CurrencyService;
import com.my.accountmanager.utils.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Service
public class CurrencyServiceImpl extends BaseCrudServiceImpl<CurrencyEntity, CurrencyRepository> implements CurrencyService {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository, RestTemplate restTemplate) {
        super(currencyRepository);
        this.restTemplate =  restTemplate;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public CurrencyThirdPartyDTO getRate(String code) {
        String byCodeParam = StringUtils.isEmpty(code) ? "" : "?base=" + code;
        String url = GlobalConstant.EXCHANGE_API_ADDRESS + byCodeParam;
        logger.info(":::::Start getRate, url: " + url);
        return restTemplate.getForObject(url, CurrencyThirdPartyDTO.class);
    }

    @Override
    public CurrencyThirdPartyDTO getRate() {
        return getRate(null);
    }

    @Override
    public Optional<CurrencyInfo> findCurrencyInfoByCode(String code) {
        Optional<CurrencyEntity> currency = this.currencyRepository.findByCode(code);
        return currency.map(this::createCurrencyInfo);
    }

    @Override
    public Optional<CurrencyEntity> findByCurrencyByCode(String code) {
        logger.info(":::::Start getCurrencyByCode");
        Optional<CurrencyEntity> currencyEntity = this.currencyRepository.findByCode(code);
        logger.info(":::::Finish getCurrencyByCode");
        return currencyEntity;
    }

    private CurrencyInfo createCurrencyInfo(CurrencyEntity currency) {
        CurrencyThirdPartyDTO rateInfo = getRate(currency.getCode());
        return new CurrencyInfo(currency, rateInfo.getRates());
    }
}
