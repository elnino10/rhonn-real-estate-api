package com.rhonn.RhonnRealEstateAPI.service.implementation;

import com.rhonn.RhonnRealEstateAPI.dto.ApiListResponse;
import com.rhonn.RhonnRealEstateAPI.dto.ApiObjectResponse;
import com.rhonn.RhonnRealEstateAPI.dto.PropertyDTO;
import com.rhonn.RhonnRealEstateAPI.exception.ResourceNotFoundException;
import com.rhonn.RhonnRealEstateAPI.mapper.PropertyMapper;
import com.rhonn.RhonnRealEstateAPI.model.Property;
import com.rhonn.RhonnRealEstateAPI.repo.PropertyRepo;
import com.rhonn.RhonnRealEstateAPI.service.ImageFileService;
import com.rhonn.RhonnRealEstateAPI.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl
        implements PropertyService
{

    @Autowired
    private PropertyRepo propertyRepo;

    @Autowired
    private ImageFileService imageFileService;

    /**
     * Creates a property
     *
     * @param propertyDTO the information to create property with
     * @return the created property
     */
    public PropertyDTO createProperty(
            PropertyDTO propertyDTO, MultipartFile mainImage,
            List<MultipartFile> propImages
    )
            throws IOException
    {

        Property prop = PropertyMapper.mapToProperty(propertyDTO);
        String imgUrl = imageFileService.saveMainImage(mainImage);    // retrieve main image url
        List<String> propImagesUrls = imageFileService.saveOtherImages(propImages);   // list of other images urls

        // add the `mainImage` and `propImages` to property field
        prop.setMainImage(imgUrl);
        prop.setPropImages(propImagesUrls);

        return PropertyMapper.mapToPropDTO(propertyRepo.save(prop));
    }

    /**
     * Gets all properties
     *
     * @return an object with message, number of all properties and the list of properties
     */
    public ResponseEntity<ApiListResponse<PropertyDTO>> getAllProperties()
    {

        List<Property> allProps = propertyRepo.findAll();
        List<PropertyDTO> propsDTOList = allProps.stream().map(PropertyMapper::mapToPropDTO)
                .collect(Collectors.toList());
        ApiListResponse<PropertyDTO> response = new ApiListResponse<>("success", HttpStatus.OK, propsDTOList.size(), propsDTOList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Gets a specific property by its id
     *
     * @param propId the id of property to find
     * @return the response with the property or a ResourceNotFoundException is thrown
     */
    public ResponseEntity<ApiObjectResponse<PropertyDTO>> getPropertyById(String propId)
    {

        Property prop = propertyRepo.findById(propId)
                .orElseThrow(() -> new ResourceNotFoundException("Property does not exist with the given id: " + propId));
        PropertyDTO propDto = PropertyMapper.mapToPropDTO(prop);
        ApiObjectResponse<PropertyDTO> response = new ApiObjectResponse<>("success", HttpStatus.OK, propDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Updates an existing property
     *
     * @param propId id of property to be updated
     * @param updatedPropDto data fields to be updated
     * @return returns response object of the updated property
     * or null if property with propId is not found
     */
    public ResponseEntity<ApiObjectResponse<PropertyDTO>> updateProperty(String propId, PropertyDTO updatedPropDto)
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
        if (updatedProp.getPropImages() != null) {
            prop.setPropImages(updatedProp.getPropImages());
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
        PropertyDTO updatedPropDTO = PropertyMapper.mapToPropDTO(prop);
        ApiObjectResponse<PropertyDTO> response = new ApiObjectResponse<>("success", HttpStatus.CREATED, updatedPropDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Deletes a property with the id propId
     *
     * @param propId the identifier of the property to be deleted
     */
    public void deleteProperty(@PathVariable String propId)
    {

        propertyRepo.findById(propId)
                .orElseThrow(() -> new ResourceNotFoundException("Property does not exist with the given id: " + propId));
        propertyRepo.deleteById(propId);
    }
}
