package com.eip.service.impl;

import com.eip.dto.request.CategoryRequest;
import com.eip.dto.response.CategoryResponse;
import com.eip.entity.Category;
import com.eip.exception.DuplicateResourceException;
import com.eip.exception.ResourceNotFoundException;
import com.eip.mapper.CategoryMapper;
import com.eip.repository.CategoryRepository;
import com.eip.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl( CategoryRepository categoryRepository )
    {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponse> getAllCategories()
    {
        return categoryRepository.findAll()
                .stream()
                .map( CategoryMapper::toResponse )
                .toList();
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request)
    {
        if ( categoryRepository.existsByNameIgnoreCase(request.name()) )
        {
            throw new DuplicateResourceException(
                    "Category already exists with name: " + request.name()
            );
        }

        Category category = new Category();
        category.setName( request.name().trim() );
        category.setDescription( request.description() );

        Category savedCategory = categoryRepository.save( category );

        return CategoryMapper.toResponse( savedCategory );
    }

    @Override
    public CategoryResponse getCategoryById( Long id )
    {
        Category category = categoryRepository.findById( id )
                .orElseThrow(() ->
                        new ResourceNotFoundException( "Category not found with id: " + id ) );

        return CategoryMapper.toResponse( category );
    }

    @Override
    public CategoryResponse updateCategory( Long id, CategoryRequest request )
    {
        Category category = categoryRepository.findById( id )
                .orElseThrow(() ->
                        new ResourceNotFoundException( "Category not found with id: " + id ) );

        category.setName( request.name().trim() );
        category.setDescription( request.description() );

        Category updatedCategory = categoryRepository.save( category );

        return CategoryMapper.toResponse( updatedCategory );
    }

    @Override
    public void deleteCategory( Long id )
    {
        if (!categoryRepository.existsById( id ) )
        {
            throw new ResourceNotFoundException( "Category not found with id: " + id );
        }

        categoryRepository.deleteById( id );
    }
}
