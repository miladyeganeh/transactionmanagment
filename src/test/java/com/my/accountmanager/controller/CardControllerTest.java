package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.CardEntity;
import com.my.accountmanager.model.dto.CardDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.service.CardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CardControllerTest {

    @Autowired
    private CardController cardController;

    @MockBean
    private CardService cardService;

    @Test
    void getAll_ifFound() {
        List<CardEntity> mockedCards = new ArrayList<>();
        CardEntity card1 = new CardEntity();
        card1.setId(1L);
        CardEntity card2 = new CardEntity();
        card2.setId(1L);
        mockedCards.add(card1);
        mockedCards.add(card2);
        when(this.cardService.getAll()).thenReturn(mockedCards);
        ResponseEntity<ResponseDTO<List<CardDTO>>> response = cardController.getAll();
        assertThat(response.getStatusCode().value(), is(HttpStatus.OK.value()));
        assertThat(Objects.requireNonNull(response.getBody()).getData().size(), is(mockedCards.size()));
    }

    @Test
    void getAll_ifNotFound() {
        List<CardEntity> mockedCards = new ArrayList<>();
        when(this.cardService.getAll()).thenReturn(mockedCards);
        ResponseEntity<ResponseDTO<List<CardDTO>>> response = cardController.getAll();
        assertThat(response.getStatusCode().value(), is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void getCard_ifFound() {
        CardEntity mockedCard = new CardEntity();
        mockedCard.setId(1L);
        when(this.cardService.findById(any())).thenReturn(Optional.of(mockedCard));
        ResponseEntity<ResponseDTO<CardDTO>> response = cardController.getCard(1L);
        assertThat(response.getStatusCode().value(), is(HttpStatus.OK.value()));
        assertThat(Objects.requireNonNull(response.getBody()).getData().getId(), is(mockedCard.getId()));
    }

    @Test
    void getCard_ifNotFound() {
        when(this.cardService.findById(any())).thenReturn(Optional.empty());
        ResponseEntity<ResponseDTO<CardDTO>> response = cardController.getCard(1L);
        assertThat(response.getStatusCode().value(), is(HttpStatus.NOT_FOUND.value()));
    }
}