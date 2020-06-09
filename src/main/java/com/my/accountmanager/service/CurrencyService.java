package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.CurrencyEntity;
import com.my.accountmanager.model.dto.CurrencyThirdParty;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
public interface CurrencyService {
    CurrencyThirdParty getRate();
    Optional<CurrencyEntity> getCurrencyByCode(Integer code);
}
