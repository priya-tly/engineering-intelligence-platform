package com.eip.dto.response;

public record ManufacturerResponse
        (
                Long id,
                String name,
                String website,
                String country
        )
{
}
