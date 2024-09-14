package com.rhonn.RhonnRealEstateAPI.service;

import com.rhonn.RhonnRealEstateAPI.ApiResponse;
import com.rhonn.RhonnRealEstateAPI.dto.PropertyDTO;
import org.springframework.http.ResponseEntity;

public interface PropertyService {

    ResponseEntity<PropertyDTO> createProperty(PropertyDTO propertyDTO);

    ResponseEntity<ApiResponse<PropertyDTO>> getAllProperties();

    ResponseEntity<PropertyDTO> getPropertyById(String propId);

    ResponseEntity<PropertyDTO> updateProperty(String propId, PropertyDTO prop);

    void deleteProperty(String propId);
}
