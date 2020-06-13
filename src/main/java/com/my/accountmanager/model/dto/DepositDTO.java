package com.my.accountmanager.model.dto;

import com.my.accountmanager.domain.entity.DepositEntity;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.Set;

public class DepositDTO implements Serializable {
    private Long id;
    private String depositNumber;
    private boolean isDefault;
    private boolean isActive;
    private String accountNumber;
    private Set<TransactionRequestDTO> transaction;

//  destination property com.my.accountmanager.domain.entity.TransactionEntity.setId() matches multiple source property hierarchies:
//
//            com.my.accountmanager.model.dto.request.TransactionRequestDTO.getTrxID()
//            com.my.accountmanager.model.dto.request.TransactionRequestDTO.getTerminalId()

    public static DepositEntity from(DepositDTO depositDTO) {
        DepositEntity depositEntity = null;
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if (depositDTO != null) {
            depositEntity = modelMapper.map(depositDTO, DepositEntity.class);
        }
        return depositEntity;
    }

    public static DepositDTO to(DepositEntity depositEntity) {
        DepositDTO depositDTO = new ModelMapper().map(depositEntity, DepositDTO.class);
        if (depositEntity.getTransaction() != null) {
            depositEntity.getTransaction().forEach(TransactionDTO::to);
        }
        return depositDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Set<TransactionRequestDTO> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<TransactionRequestDTO> transaction) {
        this.transaction = transaction;
    }
}
