package com.eip.repository;

import com.eip.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long>
{
    boolean existsByNameIgnoreCase( String name );
}
