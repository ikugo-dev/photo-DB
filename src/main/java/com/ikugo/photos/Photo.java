package com.ikugo.photos;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public class Photo {
//    @org.hibernate.validator.constraints.UUID
    private UUID id;
    @NotEmpty
    private String fileName;
    @NotEmpty
    private byte[] fileContent;

    public Photo(UUID id, String fileName, byte[] fileContent) {
        this.id = id;
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    // getters & setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public byte[] getFileContent() {
        return fileContent;
    }
    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
