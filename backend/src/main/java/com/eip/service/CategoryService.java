package com.eip.service;

import com.eip.dto.request.CategoryRequest;
import com.eip.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService
{
    List<CategoryResponse> getAllCategories();

    CategoryResponse createCategory( CategoryRequest request );

    CategoryResponse getCategoryById( Long id );

    CategoryResponse updateCategory( Long id, CategoryRequest request );

    void deleteCategory( Long id );
}
