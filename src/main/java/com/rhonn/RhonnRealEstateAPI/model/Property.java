package com.rhonn.RhonnRealEstateAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@Entity
@Getter
@Setter
//@Table(name="property")
public class Property {


    @Id
    private String propId = UUID.randomUUID().toString();
    private String propName;
    private BigDecimal price;
    private String description;
    private String address;
    private String city;
    private String state;
    private String propImage;
    private List<String> detailImages;
    private List<String> features;
    private PropCategory category;
    private PropType type;
    private LocalDateTime updatedAt = LocalDateTime.now();
    private LocalDateTime createdAt = LocalDateTime.now();

    public Property() {
    }

    public Property(String propName, BigDecimal price, String description,
                    String address, String city, String state, String propImage,
                    List<String> detailImages, List<String> features,
                    PropCategory category, PropType type) {
        this.propId = getPropId();
        this.propName = propName;
        this.price = price;
        this.description = description;
        this.address = address;
        this.city = city;
        this.state = state;
        this.propImage = propImage;
        this.detailImages = detailImages;
        this.features = features;
        this.category = category;
        this.type = type;
        this.updatedAt = getUpdatedAt();
        this.createdAt = getCreatedAt();
    }

    @Override
    public String toString() {
        return "Property{" +
                "propId='" + propId + '\'' +
                ", propName='" + propName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", propImage='" + propImage + '\'' +
                ", detailImages=" + detailImages +
                ", features=" + features +
                ", category=" + category +
                ", type=" + type +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
