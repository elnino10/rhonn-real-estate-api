package com.rhonn.RhonnRealEstateAPI.controller;

import com.rhonn.RhonnRealEstateAPI.dto.ApiListResponse;
import com.rhonn.RhonnRealEstateAPI.dto.ApiObjectResponse;
import com.rhonn.RhonnRealEstateAPI.dto.PropertyDTO;
import com.rhonn.RhonnRealEstateAPI.mapper.PropertyMapper;
import com.rhonn.RhonnRealEstateAPI.model.Property;
import com.rhonn.RhonnRealEstateAPI.service.ImageFileService;
import com.rhonn.RhonnRealEstateAPI.service.PropertyService;
import com.rhonn.RhonnRealEstateAPI.service.implementation.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController
{

    @Autowired
    private PropertyService service;

    /**
     * Creates a property in database
     *
     * @param propertyDTO the details to create property with
     * @param mainImage the mainImage file parameter
     * @param propImages the list of image files
     * @return the created property
     */
    @PostMapping
    public ResponseEntity<ApiObjectResponse<PropertyDTO>> createProperty(
            @ModelAttribute PropertyDTO propertyDTO,
            @RequestParam("mainImage") MultipartFile mainImage,
            @RequestParam("propImages") List<MultipartFile> propImages
    )
            throws IOException
    {

        if (mainImage.isEmpty()) {
            return new ResponseEntity<>(new ApiObjectResponse<>(
                    "main image is required", HttpStatus.BAD_REQUEST, null),
                    HttpStatus.BAD_REQUEST);
        }

        if (propImages == null || propImages.isEmpty()) {
            return new ResponseEntity<>(new ApiObjectResponse<>(
                    "at least one image is required", HttpStatus.BAD_REQUEST, null),
                    HttpStatus.BAD_REQUEST);
        }

        try {
            PropertyDTO savedProp = service.createProperty(propertyDTO, mainImage, propImages);

            ApiObjectResponse<PropertyDTO> response = new ApiObjectResponse<>("success", HttpStatus.CREATED, savedProp);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiObjectResponse<>("Error creating property", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    /**
     * Gets all the properties
     *
     * @return the list of all properties
     */
    @GetMapping
    public ResponseEntity<ApiListResponse<PropertyDTO>> getProperties()
    {

        return service.getAllProperties();
    }

    /**
     * Gets property from database by id
     *
     * @param propId the identifier of the property to get
     * @return the property or an exception if not found
     */
    @GetMapping("/{propId}")
    public ResponseEntity<ApiObjectResponse<PropertyDTO>> getPropertyById(@PathVariable String propId)
    {

        return service.getPropertyById(propId);
    }

    /**
     * Updates a property with the given propId
     *
     * @param propId the identifier of the property
     * @param prop the fields to update
     * @return the updated property or an exception
     */
    @PutMapping("/{propId}")
    public ResponseEntity<ApiObjectResponse<PropertyDTO>> updateProperty(@PathVariable String propId, @RequestBody PropertyDTO prop)
    {

        return service.updateProperty(propId, prop);
    }

    /**
     * Deletes property from database
     *
     * @param propId the identifier of the property to delete
     */
    @DeleteMapping("/{propId}")
    public void deleteProperty(@PathVariable String propId)
    {

        service.deleteProperty(propId);
    }
}
