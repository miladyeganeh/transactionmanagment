package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.CardEntity;
import com.my.accountmanager.model.TrxValidation;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
public interface CardService {
    Optional<CardEntity> getById(Long id);
    Iterable<CardEntity> getAll();
    boolean isValidCardForPayment(TrxValidation trxValidation);
    Optional<CardEntity> getByCardPAN(String cardPAN);
}
