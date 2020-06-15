package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.CurrencyEntity;
import com.my.accountmanager.model.dto.CurrencyInfo;
import com.my.accountmanager.model.dto.CurrencyThirdPartyDTO;

import java.util.Optional;

public interface CurrencyService extends BaseCrudService<CurrencyEntity> {
    CurrencyThirdPartyDTO getRate();
    CurrencyThirdPartyDTO getRate(String code);
    Optional<CurrencyInfo> findCurrencyInfoByCode(String code);
    Optional<CurrencyEntity> findByCurrencyByCode(String code);
}
