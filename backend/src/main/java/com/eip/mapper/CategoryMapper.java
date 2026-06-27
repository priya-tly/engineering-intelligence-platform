package com.eip.mapper;

import com.eip.dto.response.CategoryResponse;
import com.eip.entity.Category;

public class CategoryMapper
{

    private CategoryMapper ()
    {

    }

    public static CategoryResponse toResponse(Category category)
    {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
