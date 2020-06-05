package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.DepositEntity;
import com.my.accountmanager.repository.DepositRepository;
import com.my.accountmanager.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author M.Yeganeh on 31/05/2020.
 */

@Service
public class DepositServiceImpl extends BaseCrudServiceImpl<DepositEntity, DepositRepository> implements DepositService {
    @Autowired
    public DepositServiceImpl(DepositRepository repository) {
        super(repository);
    }
}
