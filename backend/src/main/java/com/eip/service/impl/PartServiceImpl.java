package com.eip.service.impl;

import com.eip.dto.request.PartRequest;
import com.eip.dto.response.PartResponse;
import com.eip.entity.Category;
import com.eip.entity.Manufacturer;
import com.eip.entity.Material;
import com.eip.entity.Part;
import com.eip.exception.DuplicateResourceException;
import com.eip.exception.ResourceNotFoundException;
import com.eip.mapper.PartMapper;
import com.eip.repository.CategoryRepository;
import com.eip.repository.ManufacturerRepository;
import com.eip.repository.MaterialRepository;
import com.eip.repository.PartRepository;
import com.eip.service.PartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartServiceImpl implements PartService
{
    private final PartRepository partRepository;
    private final CategoryRepository categoryRepository;
    private final MaterialRepository materialRepository;
    private final ManufacturerRepository manufacturerRepository;

    public PartServiceImpl(
            PartRepository partRepository,
            CategoryRepository categoryRepository,
            MaterialRepository materialRepository,
            ManufacturerRepository manufacturerRepository )
    {
        this.partRepository = partRepository;
        this.categoryRepository = categoryRepository;
        this.materialRepository = materialRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartResponse> getAllParts()
    {
        return partRepository.findAll()
                .stream()
                .map( PartMapper::toResponse )
                .toList();
    }

    @Override
    @Transactional
    public PartResponse createPart( PartRequest request )
    {
        String partNumber = request.partNumber().trim();

        if ( partRepository.existsByPartNumberIgnoreCase( partNumber ) )
        {
            throw new DuplicateResourceException(
                    "Part already exists with part number: " + partNumber
            );
        }

        Category category = categoryRepository.findById( request.categoryId() )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id: " + request.categoryId()
                        ));

        Material material = null;

        if ( request.materialId() != null )
        {
            material = materialRepository.findById( request.materialId() )
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Material not found with id: " + request.materialId()
                            ));
        }

        Manufacturer manufacturer = null;

        if ( request.manufacturerId() != null )
        {
            manufacturer = manufacturerRepository.findById( request.manufacturerId() )
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Manufacturer not found with id: "
                                            + request.manufacturerId()
                            ));
        }

        Part part = new Part();

        part.setPartNumber( partNumber );
        part.setName( request.name().trim() );
        part.setDescription( request.description() );
        part.setWeightGrams( request.weightGrams() );
        part.setStatus( request.status() );

        part.setCategory( category );
        part.setMaterial( material );
        part.setManufacturer( manufacturer );

        Part savedPart = partRepository.save( part );

        return PartMapper.toResponse( savedPart );
    }

    @Override
    @Transactional(readOnly = true)
    public PartResponse getPartById( Long id )
    {
        Part part = partRepository.findById( id )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Part not found with id: " + id
                        ));

        return PartMapper.toResponse( part );
    }

    @Override
    @Transactional
    public PartResponse updatePart( Long id, PartRequest request )
    {
        Part part = partRepository.findById( id )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Part not found with id: " + id
                        ));

        String partNumber = request.partNumber().trim();

        if ( !part.getPartNumber().equalsIgnoreCase( partNumber )
                && partRepository.existsByPartNumberIgnoreCase( partNumber ) )
        {
            throw new DuplicateResourceException(
                    "Part already exists with part number: " + partNumber
            );
        }

        Category category = categoryRepository.findById( request.categoryId() )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id: " + request.categoryId()
                        ));

        Material material = null;

        if ( request.materialId() != null )
        {
            material = materialRepository.findById( request.materialId() )
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Material not found with id: " + request.materialId()
                            ));
        }

        Manufacturer manufacturer = null;

        if ( request.manufacturerId() != null )
        {
            manufacturer = manufacturerRepository.findById( request.manufacturerId() )
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Manufacturer not found with id: "
                                            + request.manufacturerId()
                            ));
        }

        part.setPartNumber( partNumber );
        part.setName( request.name().trim() );
        part.setDescription( request.description() );
        part.setWeightGrams( request.weightGrams() );
        part.setStatus( request.status() );

        part.setCategory( category );
        part.setMaterial( material );
        part.setManufacturer( manufacturer );

        Part updatedPart = partRepository.save( part );

        return PartMapper.toResponse( updatedPart );
    }

    @Override
    @Transactional
    public void deletePart( Long id )
    {
        if ( !partRepository.existsById( id ) )
        {
            throw new ResourceNotFoundException(
                    "Part not found with id: " + id
            );
        }

        partRepository.deleteById( id );
    }
}