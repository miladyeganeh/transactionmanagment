package com.my.accountmanager.controller;

import com.my.accountmanager.model.dto.CustomerDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/customer")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "View a specific customer details", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved details"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO<CustomerDTO>> getCustomer(@PathVariable Long id) {
        logger.debug(":::::Start getCustomer, id: " + id);
        Optional<CustomerDTO> customerDTO = this.customerService.getCustomerByID(id);
        return customerDTO
                .map(customer -> ResponseEntity.ok().body(ResponseDTO.<CustomerDTO>builder().withMessage("")
                                .withData(customer)
                                .withCode(ResponseCode.FOUND_CONTENT).withDate(new Date())
                                .build()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
