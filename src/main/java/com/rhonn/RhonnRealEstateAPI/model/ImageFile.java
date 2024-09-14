package com.rhonn.RhonnRealEstateAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Entity
@Getter
@Setter
public class ImageFile {

    @Id
    private String fileId = UUID.randomUUID().toString();

    @Column(columnDefinition = "BYTEA")
    private byte[] file;
}
