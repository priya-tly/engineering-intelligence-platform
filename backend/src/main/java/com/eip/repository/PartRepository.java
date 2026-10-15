package com.eip.repository;

import com.eip.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long>
{
    boolean existsByPartNumberIgnoreCase(String partNumber);
}
