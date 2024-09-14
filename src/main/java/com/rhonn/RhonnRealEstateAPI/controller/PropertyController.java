package com.rhonn.RhonnRealEstateAPI.controller;

import com.rhonn.RhonnRealEstateAPI.dto.ApiListResponse;
import com.rhonn.RhonnRealEstateAPI.dto.ApiObjectResponse;
import com.rhonn.RhonnRealEstateAPI.dto.PropertyDTO;
import com.rhonn.RhonnRealEstateAPI.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService service;

    /**
     * Creates a property in database
     * @param propertyDTO the details to create property with
     * @return the created property
     */
    @PostMapping
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyDTO propertyDTO) {

        return service.createProperty(propertyDTO);
    }

    /**
     * Gets all the properties
     * @return the list of all properties
     */
    @GetMapping
    public ApiListResponse<PropertyDTO> getProperties() {

        return service.getAllProperties();
    }

    /**
     * Gets property from database by id
     * @param propId the identifier of the property to get
     * @return the property or an exception if not found
     */
    @GetMapping("/{propId}")
    public ApiObjectResponse<Object> getPropertyById(@PathVariable String propId) {

        return service.getPropertyById(propId);
    }

    /**
     * Updates a property with the given propId
     * @param propId the identifier of the property
     * @param prop the fields to update
     * @return the updated property or an exception
     */
    @PutMapping("/{propId}")
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable String propId, @RequestBody PropertyDTO prop) {

        return service.updateProperty(propId, prop);
    }

    /**
     * Deletes property from database
     * @param propId the identifier of the property to delete
     */
    @DeleteMapping("/{propId}")
    public void deleteProperty(@PathVariable String propId) {

        service.deleteProperty(propId);
    }
}
