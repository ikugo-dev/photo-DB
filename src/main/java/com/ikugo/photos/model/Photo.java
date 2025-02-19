package com.ikugo.photos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;

public class Photo {
    @NotEmpty
    private String fileName;
    @NotEmpty
    private String fileType;
    @NotEmpty @JsonIgnore
    private byte[] fileContent;

    public Photo(String fileName, String fileType, byte[] fileContent) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileContent = fileContent;
    }

    // getters & setters
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
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
