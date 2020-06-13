package com.my.accountmanager.business.transaction.validation.rule;

import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component("trxBalanceValidator")
public class TrxBalanceValidationRule implements ValidationRule {

    @Override
    public void validate(TrxInfo trxInfo, TransactionRequestDTO trxReq) {
        if (trxInfo.getSourceAccount() == null || trxInfo.getCurrencyInfo() == null) {
            trxInfo.addValidationError("Invalid account balance");
        } else {
            double exchangeRate = 1D;
            String baseCurrencyCode = trxInfo.getCurrencyInfo().getBaseCurrency().getCode();
            String accountCurrencyCode = trxInfo.getSourceAccount().getCurrency().getCode();
            if (!baseCurrencyCode.equals(accountCurrencyCode)) {
                exchangeRate = trxInfo.getCurrencyInfo().getExchangeRate(trxInfo.getSourceAccount().getCurrency().getCode());
            }
            Double trxAmount = trxInfo.getAmount() * exchangeRate;
            if (trxInfo.getSourceAccount() != null && trxInfo.getSourceAccount().getBalance() - trxAmount < 0) {
                trxInfo.addValidationError("Account doesnt have enough balance");
            }
        }
    }
}
