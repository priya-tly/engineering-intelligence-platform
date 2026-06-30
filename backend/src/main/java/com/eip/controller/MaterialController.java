package com.eip.controller;

import com.eip.dto.request.MaterialRequest;
import com.eip.dto.response.MaterialResponse;
import com.eip.service.MaterialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController
{
    private final MaterialService materialService;

    public MaterialController ( MaterialService materialService )
    {
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<List<MaterialResponse>> getAllMaterials()
    {
        return ResponseEntity.ok( materialService.getAllMaterials() );
    }

    @PostMapping
    public ResponseEntity<MaterialResponse> createMaterial(
            @Valid @RequestBody MaterialRequest request)
    {

        MaterialResponse response = materialService.createMaterial( request );

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( response );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponse> getMaterialById( @PathVariable Long id )
    {
        return ResponseEntity.ok( materialService.getMaterialById(id) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialResponse> updateMaterial(
            @PathVariable Long id,
            @Valid @RequestBody MaterialRequest request )
    {

        return ResponseEntity.ok( materialService.updateMaterial( id, request ) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial( @PathVariable Long id )
    {
        materialService.deleteMaterial( id );
        return ResponseEntity.noContent().build();
    }
}
