package com.nttdata.loadimageapp.controllers;

import com.nttdata.loadimageapp.repository.entity.VariantEntity;

import java.util.List;

public class ImageDTO {

    private Integer id_image;

    private String id;

    private String code;

    private String campaign;

    private int sequence;

    private String set_;

    private String tags;

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getSet_() {
        return set_;
    }

    public void setSet_(String set_) {
        this.set_ = set_;
    }

    private List<VariantEntity> variants;

    public ImageDTO(Integer id_image, String id, String code, String campaign, int sequence, String set_, String tags, List<VariantEntity> variants) {
        this.id_image = id_image;
        this.id = id;
        this.code = code;
        this.campaign = campaign;
        this.sequence = sequence;
        this.set_ = set_;
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
