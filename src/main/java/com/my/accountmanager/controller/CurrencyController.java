package com.my.accountmanager.controller;

import com.my.accountmanager.model.dto.CurrencyThirdParty;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.service.CurrencyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "api/v1/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @ApiOperation(value = "View a list of available currency exchange rate", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved currency exchange rate list."),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found.")
    })
    @GetMapping
    public ResponseEntity<ResponseDTO<CurrencyThirdParty>> getAll() {
        ResponseDTO<CurrencyThirdParty> response = new ResponseDTO<>();
        CurrencyThirdParty currencyRate = this.currencyService.getRate();
        if (currencyRate == null && currencyRate.getRates() == null) {
            response.setDate(new Date());
            response.setCode(ResponseCode.NOT_FOUND_CONTENT);
            response.setMessage("Rates were not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setDate(new Date());
        response.setCode(ResponseCode.FOUND_CONTENT);
        response.setData(currencyRate);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}


