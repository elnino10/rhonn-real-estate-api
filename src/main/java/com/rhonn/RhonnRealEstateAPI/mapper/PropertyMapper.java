package com.rhonn.RhonnRealEstateAPI.mapper;

import com.rhonn.RhonnRealEstateAPI.dto.PropertyDTO;
import com.rhonn.RhonnRealEstateAPI.model.Property;

public class PropertyMapper {

    public static PropertyDTO mapToPropDTO(Property property) {

        return new PropertyDTO(
                property.getPropId(),
                property.getPropName(),
                property.getPrice(),
                property.getDescription(),
                property.getAddress(),
                property.getCity(),
                property.getState(),
                property.getPropImage(),
                property.getDetailImages(),
                property.getFeatures(),
                property.getCategory(),
                property.getType(),
                property.getUpdatedAt(),
                property.getCreatedAt()
        );
    }

    public static Property mapToProperty(PropertyDTO propertyDTO) {

        return new Property(
                propertyDTO.getPropName(),
                propertyDTO.getPrice(),
                propertyDTO.getDescription(),
                propertyDTO.getAddress(),
                propertyDTO.getCity(),
                propertyDTO.getState(),
                propertyDTO.getPropImage(),
                propertyDTO.getDetailImages(),
                propertyDTO.getFeatures(),
                propertyDTO.getCategory(),
                propertyDTO.getType()
        );
    }
}
