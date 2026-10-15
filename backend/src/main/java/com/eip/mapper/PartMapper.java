package com.eip.mapper;

import com.eip.dto.response.PartResponse;
import com.eip.entity.Part;

public class PartMapper
{
    private PartMapper()
    {
    }

    public static PartResponse toResponse(Part part)
    {

        return new PartResponse(
                part.getId(),
                part.getPartNumber(),
                part.getName(),
                part.getDescription(),
                part.getWeightGrams(),
                part.getStatus(),

                part.getCategory().getId(),
                part.getCategory().getName(),

                part.getMaterial() != null
                        ? part.getMaterial().getId()
                        : null,

                part.getMaterial() != null
                        ? part.getMaterial().getName()
                        : null,

                part.getManufacturer() != null
                        ? part.getManufacturer().getId()
                        : null,

                part.getManufacturer() != null
                        ? part.getManufacturer().getName()
                        : null
        );
    }
}
