package com.rhonn.RhonnRealEstateAPI.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * Class for custom response of an object
 * @param <T> dynamic object type
 */
@Getter
@Setter
public class ApiObjectResponse<T> {

    private String message;
    private HttpStatus status;
    private Object data;

    /**
     * API response for a single object
     * @param message the message associated with response
     * @param status HTTP status of the response
     * @param data the data object
     */
    public ApiObjectResponse(String message, HttpStatus status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiObjectResponse{" +
                "message: '" + message + '\'' +
                ", status: " + status +
                ", data: " + data +
                '}';
    }
}
