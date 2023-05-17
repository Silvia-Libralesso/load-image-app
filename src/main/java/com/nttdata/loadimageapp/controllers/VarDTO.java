package com.nttdata.loadimageapp.controllers;

public class VarDTO {

    private String id;

    private String tags;


    private String relativePath;


    private int width;


    private int height;


    private String extension;

    public VarDTO(){

    }

    public VarDTO(String id, String tags, String relativePath, int width, int height, String extension) {
        this.id = id;
        this.tags = tags;
        this.relativePath = relativePath;
        this.width = width;
        this.height = height;
        this.extension = extension;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}