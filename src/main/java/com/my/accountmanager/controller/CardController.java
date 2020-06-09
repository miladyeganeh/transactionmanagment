package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.CardEntity;
import com.my.accountmanager.model.dto.CardDTO;
import com.my.accountmanager.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/cards") //http.localhost:8080/api/v1/card
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<CardDTO>> getAll() {
        Iterable<CardEntity> allCards = cardService.getAll();
        List<CardDTO> cardDTOS = new ArrayList<>();
        allCards.forEach(cardEntity -> {
            cardDTOS.add(CardDTO.to(cardEntity));
        });
        return ResponseEntity.status(HttpStatus.OK).body(cardDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CardDTO> getCard(@PathVariable Long id) {
        Optional<CardEntity> cardEntity = cardService.getById(id);
        if (!cardEntity.isPresent()){
            throw new RuntimeException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(CardDTO.to(cardEntity.get()));
    }
}
