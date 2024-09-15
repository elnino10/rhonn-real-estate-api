package com.rhonn.RhonnRealEstateAPI.service;

import com.rhonn.RhonnRealEstateAPI.dto.ApiObjectResponse;
import com.rhonn.RhonnRealEstateAPI.model.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageFileService
{

    ApiObjectResponse<Object> saveImageFile(MultipartFile imageFile)
            throws IOException;

    ImageFile getImageFile(String fileId);
}
