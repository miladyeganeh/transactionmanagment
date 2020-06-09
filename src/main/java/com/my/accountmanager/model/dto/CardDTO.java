package com.my.accountmanager.model.dto;

import com.my.accountmanager.domain.entity.CardEntity;
import com.my.accountmanager.domain.enums.CardType;
import org.modelmapper.ModelMapper;

public class CardDTO {

    private Long id;
    private String cardHolderName;
    private String cardPAN;
    private String pin;
    private Integer cvv;
    private String expireDateMonth;
    private String expireDateYear;
    private String hashPassword;
    private CardType type;

    public static CardEntity from(CardDTO cardDTO) {
        CardEntity cardEntity = new ModelMapper().map(cardDTO, CardEntity.class);
        return cardEntity;
    }

    public static CardDTO to(CardEntity cardEntity) {
        CardDTO cardDTO = new ModelMapper().map(cardEntity, CardDTO.class);
        cardDTO.setHashPassword("");
        return cardDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardPAN() {
        return cardPAN;
    }

    public void setCardPAN(String cardPAN) {
        this.cardPAN = cardPAN;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getExpireDateMonth() {
        return expireDateMonth;
    }

    public void setExpireDateMonth(String expireDateMonth) {
        this.expireDateMonth = expireDateMonth;
    }

    public String getExpireDateYear() {
        return expireDateYear;
    }

    public void setExpireDateYear(String expireDateYear) {
        this.expireDateYear = expireDateYear;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }
}
