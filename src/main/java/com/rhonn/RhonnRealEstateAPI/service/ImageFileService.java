package com.rhonn.RhonnRealEstateAPI.service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageFileService
{

    String saveMainImage(MultipartFile imageFile) throws IOException;

    List<String> saveOtherImages(List<MultipartFile> otherImages) throws IOException;

//    List<String> getImages();
}
