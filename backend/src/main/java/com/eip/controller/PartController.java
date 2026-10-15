package com.eip.controller;

import com.eip.dto.request.PartRequest;
import com.eip.dto.response.PartResponse;
import com.eip.service.PartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class PartController
{
    private final PartService partService;

    public PartController( PartService partService )
    {
        this.partService = partService;
    }

    @GetMapping
    public ResponseEntity<List<PartResponse>> getAllParts()
    {
        return ResponseEntity.ok( partService.getAllParts() );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartResponse> getPartById( @PathVariable Long id )
    {
        return ResponseEntity.ok( partService.getPartById( id ) );
    }

    @PostMapping
    public ResponseEntity<PartResponse> createPart(
            @Valid @RequestBody PartRequest request )
    {
        PartResponse response = partService.createPart( request );

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( response );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartResponse> updatePart(
            @PathVariable Long id,
            @Valid @RequestBody PartRequest request )
    {
        return ResponseEntity.ok(
                partService.updatePart( id, request )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePart( @PathVariable Long id )
    {
        partService.deletePart( id );

        return ResponseEntity.noContent().build();
    }
}