package com.rhonn.RhonnRealEstateAPI.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

@Configuration
public class AWSConfig
{

    @Value("${aws.s3.region}")
    private String s3Region;

    @Value("${aws.access.key.id}")
    private String keyId;

    @Value("${aws.secret.access.key}")
    private String accessKey;

    @Bean
    AmazonS3 s3Client(MultipartFile file)
    {

        // define credentials
        AWSCredentials awsCredentials = new BasicAWSCredentials(keyId, accessKey);

        return AmazonS3ClientBuilder
                .standard()
                .withRegion(s3Region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
