package com.eip.service;

import com.eip.dto.request.ManufacturerRequest;
import com.eip.dto.response.ManufacturerResponse;

import java.util.List;

public interface ManufacturerService
{
    List<ManufacturerResponse> getAllManufacturers();

    ManufacturerResponse createManufacturer( ManufacturerRequest request );

    ManufacturerResponse getManufacturerById( Long id );

    ManufacturerResponse updateManufacturer( Long id, ManufacturerRequest request );

    void deleteManufacturer ( Long id );
}
