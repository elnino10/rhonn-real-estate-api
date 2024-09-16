package com.rhonn.RhonnRealEstateAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Property
{

    @Id
    private String id = UUID.randomUUID().toString();
    private String propName;
    private BigDecimal price;
    private String description;
    private String address;
    private String city;
    private String state;
    @OneToMany(mappedBy = "prop")
    private List<ImageFile> propImages;
    private List<String> features;
    private PropCategory category;
    private PropType type;
    private LocalDateTime updatedAt = LocalDateTime.now();
    private LocalDateTime createdAt = LocalDateTime.now();

    public Property()
    {
    }

    public Property(String propName, BigDecimal price, String description,
            String address, String city, String state, List<ImageFile> propImages,
            List<String> features, PropCategory category, PropType type)
    {
        this.id = getId();
        this.propName = propName;
        this.price = price;
        this.description = description;
        this.address = address;
        this.city = city;
        this.state = state;
        this.propImages = propImages;
        this.features = features;
        this.category = category;
        this.type = type;
        this.updatedAt = getUpdatedAt();
        this.createdAt = getCreatedAt();
    }

    @Override
    public String toString()
    {
        return "Property{" +
                "id='" + id + '\'' +
                ", propName='" + propName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", propImages=" + propImages +
                ", features=" + features +
                ", category=" + category +
                ", type=" + type +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
