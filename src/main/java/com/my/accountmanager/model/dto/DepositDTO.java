package com.my.accountmanager.model.dto;

import com.my.accountmanager.domain.entity.DepositEntity;
import org.modelmapper.ModelMapper;

import java.util.Set;

public class DepositDTO {
    private Long id;
    private String depositNumber;
    private boolean isDefault;
    private Long accountId;
    private Set<TransactionDTO> transaction;

    public static DepositEntity from(DepositDTO depositDTO) {
        DepositEntity depositEntity = new ModelMapper().map(depositDTO, DepositEntity.class);
        return depositEntity;
    }

    public static DepositDTO to(DepositEntity depositEntity) {
        DepositDTO depositDTO = new ModelMapper().map(depositEntity, DepositDTO.class);
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

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
