package com.my.accountmanager.business.transaction.validation.rule;

import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import com.my.accountmanager.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("trxCardPassValidator")
public class TrxCardValidationRule implements ValidationRule {

    private final CardService cardService;

    @Autowired
    public TrxCardValidationRule(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void validate(TrxInfo trxInfo, TransactionRequestDTO trxReq) {
        if (cardService.isValidCardForPayment(trxInfo)) {
            trxInfo.addValidationError("Invalid Card");
        }
    }
}
