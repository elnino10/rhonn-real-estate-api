package com.rhonn.RhonnRealEstateAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class ImageFile
{

    @Id
    private String id = UUID.randomUUID().toString();
    @ManyToOne
    private Property prop;
    private ImageCategory imageCategory;
    private String imageUrl;
}
