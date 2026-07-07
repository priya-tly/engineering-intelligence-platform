package com.eip.controller;

import com.eip.dto.request.ManufacturerRequest;
import com.eip.dto.response.ManufacturerResponse;
import com.eip.service.ManufacturerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturer")
public class ManufacturerController
{
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService )
    {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public ResponseEntity<List<ManufacturerResponse>> getAllManufacturers()
    {
        return ResponseEntity.ok( manufacturerService.getAllManufacturers() );
    }

    @PostMapping
    public ResponseEntity<ManufacturerResponse> createManufacturer(
            @Valid @RequestBody ManufacturerRequest request)
    {

        ManufacturerResponse response = manufacturerService.createManufacturer( request );

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( response );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerResponse> getManufacturerById( @PathVariable Long id )
    {
        return ResponseEntity.ok( manufacturerService.getManufacturerById( id) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManufacturerResponse> updateManufacturer(
            @PathVariable Long id,
            @Valid @RequestBody ManufacturerRequest request )
    {

        return ResponseEntity.ok( manufacturerService.updateManufacturer( id, request ) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManufacturer( @PathVariable Long id )
    {
        manufacturerService.deleteManufacturer( id );
        return ResponseEntity.noContent().build();
    }
}
