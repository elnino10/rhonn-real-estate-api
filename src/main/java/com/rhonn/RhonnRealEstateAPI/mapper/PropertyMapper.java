package com.rhonn.RhonnRealEstateAPI.mapper;

import com.rhonn.RhonnRealEstateAPI.dto.PropertyDTO;
import com.rhonn.RhonnRealEstateAPI.model.Property;

public class PropertyMapper
{

    /**
     * Maps JPA entity to data transfer object for client
     *
     * @param property the JPA entity to be mapped
     * @return the data transfer object for client
     */
    public static PropertyDTO mapToPropDTO(Property property)
    {

        return new PropertyDTO(
                property.getId(),
                property.getPropName(),
                property.getPrice(),
                property.getDescription(),
                property.getAddress(),
                property.getCity(),
                property.getState(),
                property.getMainImage(),
                property.getPropImages(),
                property.getFeatures(),
                property.getCategory(),
                property.getType(),
                property.getUpdatedAt(),
                property.getCreatedAt()
        );
    }

    /**
     * Maps data transfer object to JPA entity
     *
     * @param propertyDTO the data transfer object to be mapped
     * @return the JPA entity
     */
    public static Property mapToProperty(PropertyDTO propertyDTO)
    {

        return new Property(
                propertyDTO.getPropName(),
                propertyDTO.getPrice(),
                propertyDTO.getDescription(),
                propertyDTO.getAddress(),
                propertyDTO.getCity(),
                propertyDTO.getState(),
                propertyDTO.getMainImage(),
                propertyDTO.getPropImages(),
                propertyDTO.getFeatures(),
                propertyDTO.getCategory(),
                propertyDTO.getType()
        );
    }
}
