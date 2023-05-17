package com.nttdata.loadimageapp.controllers;

import java.util.List;

public class ImageDTO {

    private Integer idimagen;

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

    private List<VarDTO> variants;

    public ImageDTO(Integer ididImage, String id, String code, String campaign, int sequence, String set_, String tags, List<VarDTO> variants) {
        this.idimagen = ididImage;
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

    public Integer getIdimagen() {
        return idimagen;
    }

    public void setIdimagen(Integer id_image) {
        this.idimagen = id_image;
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

    public List<VarDTO> getVariants() {
        return variants;
    }

    public void setVariants(List<VarDTO> variants) {
        this.variants = variants;
    }
}
