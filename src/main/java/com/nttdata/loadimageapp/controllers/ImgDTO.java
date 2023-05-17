package com.nttdata.loadimageapp.controllers;

public class ImgDTO {

    private Integer idimagen;
    private String id;


    public ImgDTO(){

    }

    public ImgDTO(Integer idimagen, String id) {
        this.idimagen = idimagen;
        this.id = id;
    }

    public Integer getIdimagen() {
        return idimagen;
    }

    public void setIdimagen(Integer idimagen) {
        this.idimagen = idimagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
