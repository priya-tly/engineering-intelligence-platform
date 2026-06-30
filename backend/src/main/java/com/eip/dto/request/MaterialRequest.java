package com.eip.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MaterialRequest(
        @NotBlank(message = "Material name is required")
        @Size(max = 100, message = "Material name must not exceed 100 characters")
        String name,

        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description
)
{
}
