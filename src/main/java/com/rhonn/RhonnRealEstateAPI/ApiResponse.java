package com.rhonn.RhonnRealEstateAPI;

import com.rhonn.RhonnRealEstateAPI.model.Property;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.List;

@Getter
@Setter
public class ApiResponse<T> {

    private String message;
    private int count;
    private List<T> data;

    public ApiResponse(String message, int count, List<T> data) {
        this.message = message;
        this.count = count;
        this.data = data;
    }
}
