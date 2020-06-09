package com.my.accountmanager.business.transaction.validation;

import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("trxCardPassValidator")
public class TrxCardValidator implements TrxValidator{

    private final CardService cardService;

    @Autowired
    public TrxCardValidator(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public TrxValidatorMessages validate(TrxValidation validationModel) {
        cardService.isValidCardForPayment(validationModel);
        TrxValidatorMessages trxValidatorMessages = new TrxValidatorMessages();
        trxValidatorMessages.setMessage("Invalid card");
        return trxValidatorMessages;
    }
}
