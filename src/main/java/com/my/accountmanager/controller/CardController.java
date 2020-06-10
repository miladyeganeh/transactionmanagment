package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.CardEntity;
import com.my.accountmanager.model.dto.CardDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.service.CardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
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

    @ApiOperation(value = "View a list of available cards", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved card list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public ResponseEntity<ResponseDTO<List<CardDTO>>> getAll() {
        ResponseDTO<List<CardDTO>> response = new ResponseDTO<>();
        Iterable<CardEntity> allCards = cardService.getAll();
        List<CardDTO> cardDTOS = new ArrayList<>();
        allCards.forEach(cardEntity -> {
            cardDTOS.add(CardDTO.to(cardEntity));
        });
        if (cardDTOS.isEmpty()) {
            response.setDate(new Date());
            response.setCode(ResponseCode.NOT_FOUND_CONTENT);
            response.setMessage("Cards were not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setData(cardDTOS);
        response.setCode(ResponseCode.FOUND_CONTENT);
        response.setDate(new Date());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "View a specific card details", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO<CardDTO>> getCard(@PathVariable Long id) {
        ResponseDTO<CardDTO> response = new ResponseDTO<>();
        Optional<CardEntity> cardEntity = cardService.getById(id);
        if (cardEntity.isEmpty()){
            response.setMessage("Card was not found.");
            response.setDate(new Date());
            response.setCode(ResponseCode.NOT_FOUND_CONTENT);
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setDate(new Date());
        response.setCode(ResponseCode.FOUND_CONTENT);
        response.setData(CardDTO.to(cardEntity.get()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //TODO create card end point
}
