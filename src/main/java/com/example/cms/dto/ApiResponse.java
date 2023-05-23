package com.example.cms.dto;

import lombok.Data;

@Data
public class ApiResponse {

    public ApiResponse(Object data, String message, long timeStamp) {
        this.data = data;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    private Object data;
    private String message;
    private long timeStamp;

}
