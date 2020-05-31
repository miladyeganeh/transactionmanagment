package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.CardEntity;
import com.my.accountmanager.repository.CardRepository;
import com.my.accountmanager.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl extends BaseServiceImpl<CardEntity, CardRepository> implements CardService {
    @Autowired
    public CardServiceImpl(CardRepository repository) {
        super(repository);
    }
}
