package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.CardEntity;
import com.my.accountmanager.model.MyBoolean;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.repository.CardRepository;
import com.my.accountmanager.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImpl extends BaseCrudServiceImpl<CardEntity, CardRepository> implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        super(cardRepository);
        this.cardRepository = cardRepository;
    }

    @Override
    public Optional<CardEntity> getByCardPAN(String cardPAN) {
        CardEntity byCardPAN = cardRepository.getByCardPAN(cardPAN);
        return Optional.of(byCardPAN);
    }

    @Override
    public boolean isValidCardForPayment(TrxValidation trxValidation) {
        Optional<CardEntity> cardEntity = getByCardPAN(trxValidation.getCardPAN());
        MyBoolean isValidCard = new MyBoolean(true);
        cardEntity.ifPresent(card -> {
            if (!checkHasValidPasswordAndCvv(card, trxValidation) && isExpired(card)) {
                isValidCard.setValue(false);
            }
        });
        return isValidCard.getValue();
    }

    private boolean isExpired(CardEntity cardEntity) {
        return false;
    }

    private boolean checkHasValidPasswordAndCvv(CardEntity cardEntity,TrxValidation trxValidation) {
        return cardEntity.getHashPassword().equals(trxValidation.getHashedPassword())
                && cardEntity.getCvv().equals(trxValidation.getCvv());
    }
}
