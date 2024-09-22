package com.rhonn.RhonnRealEstateAPI.service;

import com.rhonn.RhonnRealEstateAPI.dto.ApiListResponse;
import com.rhonn.RhonnRealEstateAPI.dto.ApiObjectResponse;
import com.rhonn.RhonnRealEstateAPI.dto.PropertyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PropertyService
{

    PropertyDTO createProperty(
            PropertyDTO propertyDTO, MultipartFile mainImage,
            List<MultipartFile> propImages
    ) throws IOException;

//ResponseEntity<ApiObjectResponse<PropertyDTO>> createProperty(
//            PropertyDTO propertyDTO, MultipartFile mainImage,
//            List<MultipartFile> otherImages
//    )
//            throws IOException;

    ResponseEntity<ApiListResponse<PropertyDTO>> getAllProperties();

    ResponseEntity<ApiObjectResponse<PropertyDTO>> getPropertyById(String propId);

    ResponseEntity<ApiObjectResponse<PropertyDTO>> updateProperty(String propId, PropertyDTO prop);

    void deleteProperty(String propId);
}
