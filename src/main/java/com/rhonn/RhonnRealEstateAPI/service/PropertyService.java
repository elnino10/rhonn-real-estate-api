package com.rhonn.RhonnRealEstateAPI.service;

import com.rhonn.RhonnRealEstateAPI.dto.ApiListResponse;
import com.rhonn.RhonnRealEstateAPI.dto.ApiObjectResponse;
import com.rhonn.RhonnRealEstateAPI.dto.PropertyDTO;
import org.springframework.http.ResponseEntity;

public interface PropertyService
{

    ResponseEntity<ApiObjectResponse<PropertyDTO>> createProperty(PropertyDTO propertyDTO);

    ResponseEntity<ApiListResponse<PropertyDTO>> getAllProperties();

    ResponseEntity<ApiObjectResponse<PropertyDTO>> getPropertyById(String propId);

    ResponseEntity<ApiObjectResponse<PropertyDTO>> updateProperty(String propId, PropertyDTO prop);

    void deleteProperty(String propId);
}
