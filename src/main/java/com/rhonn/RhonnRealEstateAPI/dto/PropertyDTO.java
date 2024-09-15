package com.rhonn.RhonnRealEstateAPI.dto;

import com.rhonn.RhonnRealEstateAPI.model.PropCategory;
import com.rhonn.RhonnRealEstateAPI.model.PropType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO
{

    private String propId;
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
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
