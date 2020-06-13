package com.my.accountmanager.business.transaction.validation.rule;

import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.CurrencyInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import com.my.accountmanager.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("trxCurrencyValidator")
public class TrxCurrencyValidationRule implements ValidationRule {

    private final CurrencyService currencyService;

    @Autowired
    public TrxCurrencyValidationRule(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public void validate(TrxInfo trxInfo, TransactionRequestDTO trxReq) {
        Optional<CurrencyInfo> currencyInfo = currencyService.findCurrencyInfoByCode(trxReq.getCurrencyCode());
        currencyInfo.ifPresentOrElse(trxInfo::setCurrencyInfo, () -> trxInfo.addValidationError("Invalid Currency"));
    }
}
