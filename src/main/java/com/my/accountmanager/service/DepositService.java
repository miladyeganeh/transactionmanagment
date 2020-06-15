package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.DepositEntity;
import com.my.accountmanager.model.dto.DepositDTO;

import java.util.Optional;

public interface DepositService extends BaseCrudService<DepositEntity> {
    Optional<DepositDTO> getByDepositNumber(String depositNumber);
    Optional<DepositDTO> createDeposit(DepositDTO depositDTO);
    Optional<DepositDTO> updateDeposit(DepositDTO depositDTO);
    void deleteDeposit(String depositNumber);
}
