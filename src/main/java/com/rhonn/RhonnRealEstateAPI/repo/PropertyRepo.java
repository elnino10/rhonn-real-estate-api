package com.rhonn.RhonnRealEstateAPI.repo;

import com.rhonn.RhonnRealEstateAPI.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepo
        extends JpaRepository<Property, String>
{
}
