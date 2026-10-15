package com.eip.dto.request;

import com.eip.entity.PartStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PartRequest(

        @NotBlank(message = "Part number is required")
        String partNumber,

        @NotBlank(message = "Part name is required")
        String name,

        String description,

        @Positive(message = "Weight must be greater than zero")
        BigDecimal weightGrams,

        @NotNull(message = "Part status is required")
        PartStatus status,

        @NotNull(message = "Category is required")
        Long categoryId,

        Long materialId,

        Long manufacturerId

) {
}
