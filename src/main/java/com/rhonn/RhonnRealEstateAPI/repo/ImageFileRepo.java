package com.rhonn.RhonnRealEstateAPI.repo;

import com.rhonn.RhonnRealEstateAPI.model.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageFileRepo
        extends JpaRepository<ImageFile, String>
{
}
