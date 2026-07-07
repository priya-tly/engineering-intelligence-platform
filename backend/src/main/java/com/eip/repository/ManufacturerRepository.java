package com.eip.repository;

import com.eip.entity.Manufacturer;
import com.eip.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long>
{
    boolean existsByNameIgnoreCase( String name );
}
