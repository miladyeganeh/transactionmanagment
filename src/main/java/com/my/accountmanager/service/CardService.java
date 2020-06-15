package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.CardEntity;
import com.my.accountmanager.model.TrxInfo;

import java.util.List;
import java.util.Optional;

public interface CardService extends BaseCrudService<CardEntity> {
    Optional<CardEntity> findById(Long id);
    List<CardEntity> getAll();
    boolean isValidCardForPayment(TrxInfo trxInfo);
    Optional<CardEntity> findByCardPAN(String cardPAN);
}
