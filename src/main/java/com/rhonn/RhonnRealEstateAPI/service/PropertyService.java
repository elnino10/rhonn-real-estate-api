package com.rhonn.RhonnRealEstateAPI.service;

import com.rhonn.RhonnRealEstateAPI.dto.ApiListResponse;
import com.rhonn.RhonnRealEstateAPI.dto.ApiObjectResponse;
import com.rhonn.RhonnRealEstateAPI.dto.PropertyDTO;
import org.springframework.http.ResponseEntity;

public interface PropertyService
{

    ResponseEntity<PropertyDTO> createProperty(PropertyDTO propertyDTO);

    ApiListResponse<PropertyDTO> getAllProperties();

    ApiObjectResponse<Object> getPropertyById(String propId);

    ResponseEntity<PropertyDTO> updateProperty(String propId, PropertyDTO prop);

    void deleteProperty(String propId);
}
