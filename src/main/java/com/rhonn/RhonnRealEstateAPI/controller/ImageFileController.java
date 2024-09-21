package com.rhonn.RhonnRealEstateAPI.controller;

import com.rhonn.RhonnRealEstateAPI.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    @PostMapping("/upload-image")
    public String saveImage(@RequestBody MultipartFile imageFile)
            throws IOException
    {

        return imageFileService.saveMainImage(imageFile);
    }

    @PostMapping("/upload-other-images")
    public List<String> saveOtherImages(@RequestBody List<MultipartFile> otherFiles)
            throws IOException
    {

        return imageFileService.saveOtherImages(otherFiles);
    }
}
