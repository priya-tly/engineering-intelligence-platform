package com.eip.mapper;

import com.eip.dto.response.ManufacturerResponse;
import com.eip.entity.Manufacturer;

public class ManufacturerMapper
{

    private ManufacturerMapper()
    {

    }

    public static ManufacturerResponse toResponse(Manufacturer manufacturer )
    {
        return new ManufacturerResponse(
                manufacturer.getId(),
                manufacturer.getName(),
                manufacturer.getWebsite(),
                manufacturer.getCountry()
        );
    }
}
