package com.rhonn.RhonnRealEstateAPI.service.implementation;

import com.amazonaws.services.s3.AmazonS3;
import com.rhonn.RhonnRealEstateAPI.model.ImageFile;
import com.rhonn.RhonnRealEstateAPI.repo.ImageFileRepo;
import com.rhonn.RhonnRealEstateAPI.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageFileServiceImpl
        implements ImageFileService
{

    @Autowired
    ImageFileRepo imageFileRepo;

    AmazonS3 s3Client;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    /**
     * Saves main image file to cloud storage
     *
     * @param imageFile file to be stored in database
     * @return url of the uploaded file
     * @throws IOException i/o exception
     */
    @Override
    public String saveMainImage(MultipartFile imageFile)
            throws IOException
    {

        String fileUrl = uploadToS3Bucket(imageFile);
        ImageFile image = new ImageFile();
        image.setImageUrl(fileUrl);
        imageFileRepo.save(image);  // save image to database

        return fileUrl;
    }

    /**
     * Saves other images to cloud storage
     * @param files the list files to be saved
     * @return the list of urls for uploaded files
     * @throws IOException i/o exception
     */
    public List<String> saveOtherImages(List<MultipartFile> files)
            throws IOException
    {

        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            ImageFile image = new ImageFile();
            String imageUrl = uploadToS3Bucket(file);
            image.setImageUrl(imageUrl);
            imageUrls.add(imageUrl);
        }

        return imageUrls;
    }

    /**
     * Uploads file to cloud bucket
     *
     * @param file the multipart file to be uploaded
     * @return the url of the uploaded file
     * @throws IOException i/o exception
     */
    private String uploadToS3Bucket(MultipartFile file)
            throws IOException
    {
        if (!file.isEmpty() && file.getOriginalFilename() != null) {

            String fileName = Paths.get(file.getOriginalFilename()).getFileName().toString();

            // define client put request
            s3Client.putObject(bucketName, fileName, file.getInputStream(), null);

            // return file url
            return String.format("https://%s.s3.amazonaws.com/%s", bucketName, fileName);
        }
        return null;
    }
}
