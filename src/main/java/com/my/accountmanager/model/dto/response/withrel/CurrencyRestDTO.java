package com.my.accountmanager.model.dto.response.withrel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.my.accountmanager.domain.entity.CurrencyEntity;
import org.springframework.hateoas.RepresentationModel;

public class CurrencyRestDTO extends RepresentationModel<CurrencyRestDTO> {

    private Long id;
    private String code;
    private String name;

    public static CurrencyRestDTO from (CurrencyEntity currencyEntity) {
        CurrencyRestDTO currencyRestDTO = new CurrencyRestDTO();
        currencyRestDTO.setId(currencyEntity.getId());
        currencyRestDTO.setCode(currencyEntity.getCode());
        currencyRestDTO.setName(currencyEntity.getName());
        return currencyRestDTO;
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
