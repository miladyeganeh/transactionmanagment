package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.CardEntity;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.repository.CardRepository;
import com.my.accountmanager.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CardServiceImpl extends BaseCrudServiceImpl<CardEntity, CardRepository> implements CardService {
    private static final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        super(cardRepository);
        this.cardRepository = cardRepository;
    }

    @Override
    public Optional<CardEntity> findByCardPAN(String cardPAN) {
        logger.debug(":::::Start getByCardPAN, cardPAN:" + cardPAN);
        Optional<CardEntity> cardEntity = cardRepository.findByCardPAN(cardPAN);
        logger.debug(":::::Finish getByCardPAN");
        return cardEntity;
    }

    @Override
    public boolean isValidCardForPayment(TrxInfo trxValidation) {
        logger.debug(":::::Start isValidCardForPayment, cardPAN:" + trxValidation.getCardPAN());
        Optional<CardEntity> cardEntity = findByCardPAN(trxValidation.getCardPAN());
        logger.debug(":::::Finish isValidCardForPayment");
        return cardEntity
                .map(card -> !checkHasValidPasswordAndCvv(card, trxValidation) && isExpired(card))
                .orElse(false);
    }

    private boolean isExpired(CardEntity cardEntity) {
        return cardEntity.getExpireDateMonth() < LocalDate.now().getMonthValue()
                && cardEntity.getExpireDateYear() < LocalDate.now().getYear();
    }
    private boolean checkHasValidPasswordAndCvv(CardEntity cardEntity, TrxInfo trxInfo) {
        return cardEntity.getHashPassword().equals(trxInfo.getHashedPassword())
                && cardEntity.getCvv().equals(trxInfo.getCvv());
    }
}
