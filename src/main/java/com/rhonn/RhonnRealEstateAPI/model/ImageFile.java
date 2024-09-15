package com.rhonn.RhonnRealEstateAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class ImageFile
{

    @Id
    private String fileId = UUID.randomUUID().toString();

    @Column(columnDefinition = "BYTEA")
    private byte[] file;
}
