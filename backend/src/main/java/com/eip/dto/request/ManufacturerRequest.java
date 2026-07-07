package com.eip.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ManufacturerRequest
        (
        @NotBlank(message = "Manufacturer name is required")
        @Size(max = 100, message = "Manufacturer name must not exceed 100 characters")
        String name,

        @Size(max = 500, message = "website name must not exceed 500 characters")
        String website,

        @Size(max = 100, message = "country name must not exceed 100 characters")
        String country
)
{
}
