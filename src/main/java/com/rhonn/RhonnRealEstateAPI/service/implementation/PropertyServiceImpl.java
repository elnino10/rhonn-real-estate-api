package com.rhonn.RhonnRealEstateAPI.service.implementation;

import com.rhonn.RhonnRealEstateAPI.dto.ApiListResponse;
import com.rhonn.RhonnRealEstateAPI.dto.ApiObjectResponse;
import com.rhonn.RhonnRealEstateAPI.dto.PropertyDTO;
import com.rhonn.RhonnRealEstateAPI.exception.ResourceNotFoundException;
import com.rhonn.RhonnRealEstateAPI.mapper.PropertyMapper;
import com.rhonn.RhonnRealEstateAPI.model.Property;
import com.rhonn.RhonnRealEstateAPI.repo.PropertyRepo;
import com.rhonn.RhonnRealEstateAPI.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl
        implements PropertyService
{

    @Autowired
    private PropertyRepo propertyRepo;

    /**
     * Creates a property
     *
     * @param propertyDTO the information to create property with
     * @return the created property
     */
    public ResponseEntity<PropertyDTO> createProperty(PropertyDTO propertyDTO)
    {

        Property prop = PropertyMapper.mapToProperty(propertyDTO);
        PropertyDTO savedProp = PropertyMapper.mapToPropDTO(propertyRepo.save(prop));

        return new ResponseEntity<>(savedProp, HttpStatus.CREATED);
    }

    /**
     * Gets all properties
     *
     * @return an object with message, number of all properties and the list of properties
     */
    public ApiListResponse<PropertyDTO> getAllProperties()
    {

        List<Property> allProps = propertyRepo.findAll();
        List<PropertyDTO> propsDTOList = allProps.stream().map(PropertyMapper::mapToPropDTO)
                .collect(Collectors.toList());
        return new ApiListResponse<>("success", HttpStatus.OK, propsDTOList.size(), propsDTOList);
    }

    /**
     * Gets a specific property by its id
     *
     * @param propId the id of property to find
     * @return the response with the property or a ResourceNotFoundException is thrown
     */
    public ApiObjectResponse<Object> getPropertyById(String propId)
    {

        Property prop = propertyRepo.findById(propId)
                .orElseThrow(() -> new ResourceNotFoundException("Property does not exist with the given id: " + propId));
        PropertyDTO propDto = PropertyMapper.mapToPropDTO(prop);

        return new ApiObjectResponse<>("success", HttpStatus.OK, propDto);
    }

    /**
     * Updates an existing property
     *
     * @param propId id of property to be updated
     * @param updatedPropDto data fields to be updated
     * @return returns response object of the updated property
     * or null if property with propId is not found
     */
    public ResponseEntity<PropertyDTO> updateProperty(String propId, PropertyDTO updatedPropDto)
    {

        Property prop = propertyRepo.findById(propId)
                .orElseThrow(() -> new ResourceNotFoundException("Property does not exist with the given id: " + propId));
        Property updatedProp = PropertyMapper.mapToProperty(updatedPropDto);

        // Update only if the new value is not null
        if (updatedProp.getPropName() != null) {
            prop.setPropName(updatedProp.getPropName());
        }
        if (updatedProp.getPrice() != null) {
            prop.setPrice(updatedProp.getPrice());
        }
        if (updatedProp.getDescription() != null) {
            prop.setDescription(updatedProp.getDescription());
        }
        if (updatedProp.getAddress() != null) {
            prop.setAddress(updatedProp.getAddress());
        }
        if (updatedProp.getCity() != null) {
            prop.setCity(updatedProp.getCity());
        }
        if (updatedProp.getState() != null) {
            prop.setState(updatedProp.getState());
        }
        if (updatedProp.getPropImage() != null) {
            prop.setPropImage(updatedProp.getPropImage());
        }
        if (updatedProp.getDetailImages() != null) {
            prop.setDetailImages(updatedProp.getDetailImages());
        }
        if (updatedProp.getFeatures() != null) {
            prop.setFeatures(updatedProp.getFeatures());
        }
        if (updatedProp.getCategory() != null) {
            prop.setCategory(updatedProp.getCategory());
        }
        if (updatedProp.getType() != null) {
            prop.setType(updatedProp.getType());
        }
        if (updatedProp.getUpdatedAt() != null) {
            prop.setUpdatedAt(updatedProp.getUpdatedAt());
        }
        propertyRepo.save(prop);

        return new ResponseEntity<>(PropertyMapper.mapToPropDTO(prop), HttpStatus.CREATED);
    }

    /**
     * Deletes a property with the id propId
     *
     * @param propId the identifier of the property to be deleted
     */
    public void deleteProperty(@PathVariable String propId)
    {

        Property prop = propertyRepo.findById(propId)
                .orElseThrow(() -> new ResourceNotFoundException("Property does not exist with the given id: " + propId));
        propertyRepo.deleteById(prop.getPropId());
    }
}
