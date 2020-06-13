package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.DocumentEntity;
import com.my.accountmanager.model.dto.response.withrel.DocumentRestDTO;
import com.my.accountmanager.service.DocumentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/v1/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @ApiOperation(value = "View a specific document details", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved details"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<DocumentRestDTO> getDocument(@PathVariable Long id) {
        Optional<DocumentEntity> documentEntity = this.documentService.findById(id);
        if (documentEntity.isPresent()) {
            DocumentRestDTO documentRestDTO = DocumentRestDTO.from(documentEntity.get());
            documentRestDTO.add(linkTo(methodOn(DocumentController.class).getDocument(documentRestDTO.getId())).withSelfRel());
            documentRestDTO.add(linkTo(methodOn(CurrencyController.class).getCurrency(documentRestDTO.getCurrencyID())).withRel("Currency"));
            return ResponseEntity.ok(documentRestDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
