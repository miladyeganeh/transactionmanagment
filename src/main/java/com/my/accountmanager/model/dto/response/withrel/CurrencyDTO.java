package com.my.accountmanager.model.dto.response.withrel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.my.accountmanager.domain.entity.CurrencyEntity;

public class CurrencyDTO {

    private Long id;
    private String code;
    private String name;

    public static CurrencyDTO from (CurrencyEntity currencyEntity) {
        CurrencyDTO currencyRestDTO = new CurrencyDTO();
        currencyRestDTO.setId(currencyEntity.getId());
        currencyRestDTO.setCode(currencyEntity.getCode());
        currencyRestDTO.setName(currencyEntity.getName());
        return currencyRestDTO;
    }

    public static CurrencyEntity to (CurrencyDTO currencyDTO) {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(currencyDTO.getId());
        currencyEntity.setCode(currencyDTO.getCode());
        currencyEntity.setName(currencyDTO.getName());
        return currencyEntity;
    }

    public Long getId() {
        return id;
    }

    @JsonCreator
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    @JsonCreator
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    @JsonCreator
    public void setName(String name) {
        this.name = name;
    }
}
