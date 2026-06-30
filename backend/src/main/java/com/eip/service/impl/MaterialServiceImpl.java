package com.eip.service.impl;

import com.eip.dto.request.MaterialRequest;
import com.eip.dto.response.MaterialResponse;
import com.eip.entity.Material;
import com.eip.exception.DuplicateResourceException;
import com.eip.exception.ResourceNotFoundException;
import com.eip.mapper.MaterialMapper;
import com.eip.repository.MaterialRepository;
import com.eip.service.MaterialService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService
{

    private final MaterialRepository materialRepository;

    public MaterialServiceImpl( MaterialRepository materialRepository )
    {
        this.materialRepository = materialRepository;
    }

    @Override
    public List<MaterialResponse> getAllMaterials()
    {
        return materialRepository.findAll()
                .stream()
                .map( MaterialMapper::toResponse )
                .toList();
    }

    @Override
    public MaterialResponse createMaterial(MaterialRequest request)
    {
       if( materialRepository.existsByNameIgnoreCase( request.name() ))
       {
           throw new DuplicateResourceException(
                   "Material already exists with name: " + request.name()
           );
       }

        Material material = new Material();
        material.setName( request.name() );
        material.setDescription( request.description() );

        Material savedMaterial = materialRepository.save( material );

        return MaterialMapper.toResponse( savedMaterial );
    }

    @Override
    public MaterialResponse getMaterialById(Long id)
    {
        Material material = materialRepository.findById( id )
                .orElseThrow( ()->
                         new ResourceNotFoundException( "Material not found" ) );
        return MaterialMapper.toResponse( material );
    }

    @Override
    public MaterialResponse updateMaterial(Long id, MaterialRequest request)
    {
        Material material = materialRepository.findById( id )
                .orElseThrow( ()->
                        new ResourceNotFoundException( "Material not found" ) );
        material.setName( request.name().trim() );
        material.setDescription( request.description().trim() );

        Material updatedMaterial = materialRepository.save( material );

        return MaterialMapper.toResponse( updatedMaterial );
    }

    @Override
    public void deleteMaterial(Long id)
    {
        if( !materialRepository.existsById( id ) )
        {
            throw new ResourceNotFoundException( "Material not found with id: " + id );
        }
       materialRepository.deleteById( id );
    }

}
