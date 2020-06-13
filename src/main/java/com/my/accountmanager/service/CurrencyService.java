package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.CurrencyEntity;
import com.my.accountmanager.model.dto.CurrencyInfo;
import com.my.accountmanager.model.dto.CurrencyThirdPartyDTO;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
public interface CurrencyService extends BaseCrudService<CurrencyEntity> {
    CurrencyThirdPartyDTO getRate();
    CurrencyThirdPartyDTO getRate(String code);
    Optional<CurrencyInfo> findCurrencyInfoByCode(String code);
    Optional<CurrencyEntity> findByCurrencyByCode(String code);
}
