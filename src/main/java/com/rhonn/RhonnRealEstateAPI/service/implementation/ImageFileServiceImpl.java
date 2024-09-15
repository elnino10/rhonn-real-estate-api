package com.rhonn.RhonnRealEstateAPI.service.implementation;

import com.rhonn.RhonnRealEstateAPI.dto.ApiObjectResponse;
import com.rhonn.RhonnRealEstateAPI.exception.ResourceNotFoundException;
import com.rhonn.RhonnRealEstateAPI.model.ImageFile;
import com.rhonn.RhonnRealEstateAPI.repo.ImageFileRepo;
import com.rhonn.RhonnRealEstateAPI.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageFileServiceImpl
        implements ImageFileService
{

    @Autowired
    ImageFileRepo imageFileRepo;

    /**
     * Saves image file to database
     *
     * @param imageFile file to be stored in database
     * @throws IOException thrown exception
     */
    @Override
    public ApiObjectResponse<Object> saveImageFile(MultipartFile imageFile)
            throws IOException
    {

        ImageFile file = new ImageFile();
        file.setFile(imageFile.getBytes());
        imageFileRepo.save(file);

        return new ApiObjectResponse<>("success", HttpStatus.CREATED, file.getFileId());
    }

    /**
     * Downloads image file from database
     *
     * @param fileId the identifier of image to download
     * @return the image file or a ResourceNotFoundException if not found
     */
    public ImageFile getImageFile(String fileId)
    {

        return imageFileRepo.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("Image file of id: " + fileId + "does not exist"));
    }
}
