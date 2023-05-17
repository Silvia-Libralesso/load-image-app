package com.nttdata.loadimageapp.domain.model;

import com.nttdata.loadimageapp.controllers.VarDTO;

import java.util.List;

public class Image {

    private Integer idimgen;

    private String id;

    private String code;

    private String campaign;

    private int sequence;

    private String set_;


    private String tags;


    private List<VarDTO> variants;


    public Image(){

    }

    /*
    public Image(ImageEntity image) { //poner aquí constructor con atributos en lugar de con objeto imageEntity????
        this.id = image.getId();
        this.code = image.getCode();
        this.campaign = image.getCampaign();
        this.sequence = image.getSequence();
        this.set_ = image.getSet_();
        this.tags = image.getTags();

    }
    */



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdimgen() {
        return idimgen;
    }

    public void setIdimgen(Integer id) {
        this.idimgen = id;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    //@JsonManagedReference
    public List<VarDTO> getVariants(){
        return variants;
    }

    public void setVariants(List<VarDTO> variants) {
        this.variants = variants;
    }
}
