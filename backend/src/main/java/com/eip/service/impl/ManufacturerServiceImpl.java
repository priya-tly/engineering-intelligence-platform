package com.eip.service.impl;

import com.eip.dto.request.ManufacturerRequest;
import com.eip.dto.response.ManufacturerResponse;
import com.eip.entity.Manufacturer;
import com.eip.exception.DuplicateResourceException;
import com.eip.exception.ResourceNotFoundException;
import com.eip.mapper.ManufacturerMapper;
import com.eip.repository.ManufacturerRepository;
import com.eip.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService
{

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository )
    {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<ManufacturerResponse> getAllManufacturers()
    {
        return manufacturerRepository.findAll()
                .stream()
                .map( ManufacturerMapper::toResponse )
                .toList();
    }

    @Override
    public ManufacturerResponse createManufacturer(ManufacturerRequest request)
    {
       if( manufacturerRepository.existsByNameIgnoreCase( request.name() ))
       {
           throw new DuplicateResourceException(
                   "Manufacturer already exists with name: " + request.name()
           );
       }

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName( request.name() );
        manufacturer.setWebsite( request.website() );
        manufacturer.setCountry( request.country() );

        Manufacturer savedManufacturer = manufacturerRepository.save( manufacturer );

        return ManufacturerMapper.toResponse( savedManufacturer );
    }

    @Override
    public ManufacturerResponse getManufacturerById(Long id)
    {
        Manufacturer manufacturer = manufacturerRepository.findById( id )
                .orElseThrow( ()->
                         new ResourceNotFoundException( "Manufacturer not found" ) );
        return ManufacturerMapper.toResponse( manufacturer );
    }

    @Override
    public ManufacturerResponse updateManufacturer(Long id, ManufacturerRequest request)
    {
        Manufacturer manufacturer = manufacturerRepository.findById( id )
                .orElseThrow( ()->
                        new ResourceNotFoundException( "Manufacturer not found" ) );
        manufacturer.setName( request.name().trim() );
        manufacturer.setWebsite( request.website().trim() );
        manufacturer.setCountry( request.country().trim() );

        Manufacturer updatedManufacturer = manufacturerRepository.save( manufacturer );

        return ManufacturerMapper.toResponse( updatedManufacturer );
    }

    @Override
    public void deleteManufacturer(Long id)
    {
        if( !manufacturerRepository.existsById( id ) )
        {
            throw new ResourceNotFoundException( "Manufacturer not found with id: " + id );
        }
        manufacturerRepository.deleteById( id );
    }

}
