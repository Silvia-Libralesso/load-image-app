package com.nttdata.loadimageapp.repository.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table (name="image")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ImageEntity {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_image;

    @Column (name="id")
    @NotBlank (message = "El campo id_image no puede estar vacío.")
    private String id;

    @Column (name="code")
    @NotBlank (message = "El campo code no puede estar vacío.")
    private String code;

    @Column (name="campaign")
    @NotBlank (message = "El campo campaign no puede estar vacío.")
    private String campaign;

    @Column (name="sequence")
    @NotNull(message = "El campo sequence no puede ser nulo.")
    private int sequence;

    @Column (name = "set_")
    @JsonProperty("set") //para que el campo en el json se llame así, y no con el nombre que tiene en la BD
    @NotBlank (message = "El campo set_ no puede ser nulo.") //
    private String set_;

    @Column (name = "tags")
    @NotBlank (message = "El campo tags no puede estar vacío.")
    private String tags;

    @OneToMany (mappedBy = "image", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <VariantEntity> variantEntities;


    public ImageEntity(){
        //vacío para el framework
    }

    public ImageEntity(Integer id_image, String id, String code, String campaign, int sequence, String set_, String tags, List<VariantEntity> variantEntities) {
        this.id_image = id_image;
        this.id = id;
        this.code = code;
        this.campaign = campaign;
        this.sequence = sequence;
        this.set_ = set_;
        this.tags = tags;
        this.variantEntities = variantEntities;
    }

/*
    public ImageEntity(Image image){
        this.id_image = image.getIdImage();
        this.id = image.getId();
        this.code = image.getCode();
        this.campaign = image.getCampaign();
        this.sequence = image.getSequence();
        this.set_ = image.getSet_();
        this.tags = image.getTags();
        this.variantEntities = this.getVariants();
    }



    //para "convertir" un ImageEntity a Image
    public Image toImage(){
        Image image = new Image();
        BeanUtils.copyProperties(this, image);
        return image;
    }

    */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdImage() {
        return id_image;
    }

    public void setIdImage(Integer id) {
        this.id_image = id;
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
    public List<VariantEntity> getVariants(){
        return variantEntities;
    }

    public void setVariantEntities(List<VariantEntity> variantEntities) {
        this.variantEntities = variantEntities;
    }
}
