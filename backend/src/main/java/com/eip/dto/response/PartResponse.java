package com.eip.dto.response;

import com.eip.entity.PartStatus;

import java.math.BigDecimal;

public record PartResponse
        (

        Long id,
        String partNumber,
        String name,
        String description,
        BigDecimal weightGrams,
        PartStatus status,
        Long categoryId,
        String categoryName,
        Long materialId,
        String materialName,
        Long manufacturerId,
        String manufacturerName

        )
{
}
