package com.nttdata.loadimageapp.controllers;

public class ImgDTO {

    private String id;

    public ImgDTO(){

    }
    public ImgDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
