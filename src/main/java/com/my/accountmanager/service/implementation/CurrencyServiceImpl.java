package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.CurrencyEntity;
import com.my.accountmanager.repository.CurrencyRepository;
import com.my.accountmanager.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Service
public class CurrencyServiceImpl extends BaseServiceImpl<CurrencyEntity, CurrencyRepository> implements CurrencyService {
    @Autowired
    public CurrencyServiceImpl(CurrencyRepository repository) {
        super(repository);
    }
}
