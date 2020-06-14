package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.CardEntity;
import com.my.accountmanager.model.dto.CardDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.service.CardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/cards")
public class CardController {
    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @ApiOperation(value = "View a list of available cards", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved card list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public ResponseEntity<ResponseDTO<List<CardDTO>>> getAll() {
        logger.debug(":::::Start getAll");
        List<CardEntity> allCards = cardService.getAll();
        if (allCards.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<CardDTO> cardDTOS = allCards.stream().map(CardDTO::from).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseDTO.<List<CardDTO>>builder()
                .withData(cardDTOS)
                .withDate(new Date())
                .withCode(ResponseCode.FOUND_CONTENT)
                .build());
    }

    @ApiOperation(value = "View a specific card details", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved card"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO<CardDTO>> getCard(@PathVariable Long id) {
        logger.debug(":::::Start getCard, id:" + id);
        Optional<CardEntity> cardEntity = cardService.findById(id);
        return cardEntity.map(card -> ResponseEntity.ok(ResponseDTO.<CardDTO>builder()
                .withData(CardDTO.from(card))
                .withDate(new Date())
                .withCode(ResponseCode.FOUND_CONTENT)
                .build())).orElse(ResponseEntity.notFound().build());
    }
}
