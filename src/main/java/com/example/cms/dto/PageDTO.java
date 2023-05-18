package com.example.cms.dto;

import lombok.Data;

@Data
public class PageDTO {

    public PageDTO(Object content, long size, int totalPages, long totalRecords) {
        this.content = content;
        this.size = size;
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
    }

    private Object content;

    private long size;

    private int totalPages;

    private long totalRecords;

}
