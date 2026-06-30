package com.eip.mapper;

import com.eip.dto.response.MaterialResponse;
import com.eip.entity.Material;

public class MaterialMapper
{

    private MaterialMapper ()
    {

    }

    public static MaterialResponse toResponse( Material material )
    {
        return new MaterialResponse(
                material.getId(),
                material.getName(),
                material.getDescription()
        );
    }
}
