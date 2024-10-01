package com.rhonn.RhonnRealEstateAPI.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Class for custom response of objects array
 *
 * @param <T> dynamic object type
 */
@Getter
@Setter
public class ApiListResponse<T>
{

    private String message;
    private HttpStatus status;
    private int count;
    private List<T> data;

    /**
     * Constructor for the ApiListResponse class
     *
     * @param message custom response message
     * @param status response HTTP status
     * @param count number of objects in the returned data
     * @param data the array of objects
     */
    public ApiListResponse(String message, HttpStatus status, int count, List<T> data)
    {
        this.message = message;
        this.status = status;
        this.count = count;
        this.data = data;
    }
}
