package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.DocumentEntity;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.withrel.DocumentRestDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.service.DocumentService;
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
@RequestMapping(value = "api/v1/documents")
public class DocumentController {
    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @ApiOperation(value = "View a specific document details", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved details"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO<DocumentRestDTO>> getDocument(@PathVariable Long id) {
        logger.debug(":::::Start getDocument, id: " + id);
        Optional<DocumentEntity> documentEntity = this.documentService.findById(id);
        return documentEntity
                .map(document -> ResponseEntity.ok().body(ResponseDTO.<DocumentRestDTO>builder().withMessage("")
                        .withData(DocumentRestDTO.from(document))
                        .withCode(ResponseCode.FOUND_CONTENT).withDate(new Date())
                        .build()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
