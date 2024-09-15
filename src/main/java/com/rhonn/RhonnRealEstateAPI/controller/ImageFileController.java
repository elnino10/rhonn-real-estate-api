package com.rhonn.RhonnRealEstateAPI.controller;

import com.rhonn.RhonnRealEstateAPI.dto.ApiObjectResponse;
import com.rhonn.RhonnRealEstateAPI.model.ImageFile;
import com.rhonn.RhonnRealEstateAPI.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageFileController
{

    @Autowired
    ImageFileService imageFileService;

    /**
     * Uploads image file to database
     *
     * @param imageFile the multipart file
     * @throws IOException exception
     */
    @PostMapping("/upload")
    public ApiObjectResponse<Object> saveImage(@RequestBody MultipartFile imageFile)
            throws IOException
    {

        return imageFileService.saveImageFile(imageFile);
    }

    /**
     * Downloads image file from database
     *
     * @param imageFileId the file identifier
     * @return the image file or a ResourceNotFoundException if not found
     */
    @GetMapping("/{imageFileId}/download")
    public ImageFile getImage(@RequestParam("imageFile") String imageFileId)
    {

        return imageFileService.getImageFile(imageFileId);
    }
}
