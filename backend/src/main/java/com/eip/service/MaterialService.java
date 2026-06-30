package com.eip.service;

import com.eip.dto.request.MaterialRequest;
import com.eip.dto.response.MaterialResponse;

import java.util.List;

public interface MaterialService
{
    List<MaterialResponse> getAllMaterials();

    MaterialResponse createMaterial( MaterialRequest request );

    MaterialResponse getMaterialById( Long id );

    MaterialResponse updateMaterial( Long id, MaterialRequest request );

    void deleteMaterial ( Long id );
}
