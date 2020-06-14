package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.CurrencyEntity;
import com.my.accountmanager.model.dto.CurrencyThirdPartyDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.withrel.CurrencyDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.service.CurrencyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @ApiOperation(value = "View a list of available currency exchange rate", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved currency exchange rate list."),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found.")
    })
    @GetMapping(value = "/exhgangerate")
    public ResponseEntity<ResponseDTO<CurrencyThirdPartyDTO>> getExchangesRate() {
        CurrencyThirdPartyDTO rates = this.currencyService.getRate();
        if (rates != null) {
            return ResponseEntity.ok().body(ResponseDTO.<CurrencyThirdPartyDTO>builder()
                    .withData(rates)
                    .withDate(new Date())
                    .withCode(ResponseCode.FOUND_CONTENT)
                    .build());
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "View a specific currency details", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved details"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO<CurrencyDTO>> getCurrency(@PathVariable Long id) {
        Optional<CurrencyEntity> currencyEntity = this.currencyService.findById(id);
        return currencyEntity
                .map(currency ->   ResponseEntity.ok().body(ResponseDTO.<CurrencyDTO>builder().withMessage("")
                        .withData(CurrencyDTO.from(currency))
                        .withCode(ResponseCode.FOUND_CONTENT).withDate(new Date())
                        .build()))
                .orElse(ResponseEntity.notFound().build());
    }

}


