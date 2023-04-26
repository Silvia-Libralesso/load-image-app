package com.nttdata.loadimageapp.controllers;

import com.nttdata.loadimageapp.repository.entity.ImageEntity;

public class VariantDTO {

    private Integer id;


    private String tags;


    private String relativePath;


    private int width;


    private int height;


    private String extension;


    private ImageEntity image;


    public VariantDTO(Integer id, String tags, String relativePath, int width, int height, String extension, ImageEntity image) {
        this.id = id;
        this.tags = tags;
        this.relativePath = relativePath;
        this.width = width;
        this.height = height;
        this.extension = extension;
        this.image = image;
    }

    public VariantDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }
}
