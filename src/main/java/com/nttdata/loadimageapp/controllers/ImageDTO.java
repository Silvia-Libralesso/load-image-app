package com.nttdata.loadimageapp.controllers;

import com.nttdata.loadimageapp.repository.entity.VariantEntity;

import java.util.List;

public class ImageDTO {

    private Integer id_image;

    private String id;

    private String code;

    private String campaign;

    private String tags;

    private List<VariantEntity> variants;

    public ImageDTO(Integer id_image, String id, String code, String campaign, String tags, List<VariantEntity> variants) {
        this.id_image = id_image;
        this.id = id;
        this.code = code;
        this.campaign = campaign;
        this.tags = tags;
        this.variants = variants;
    }


    public ImageDTO() {
    }

    public Integer getIdImage() {
        return id_image;
    }

    public void setIdImage(Integer id_image) {
        this.id_image = id_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<VariantEntity> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantEntity> variants) {
        this.variants = variants;
    }
}
