package com.nttdata.loadimageapp.repository.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name="image")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ImageEntity {


    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idimagen;

    @Column (name="id")
    @NotBlank (message = "El campo id no puede estar vacío.")
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
    //@JsonProperty("set") //para que el campo en el json se llame así, y no con el nombre que tiene en la BD
    //@NotBlank (message = "El campo set_ no puede ser nulo.") //
    private String set_;

    @Column (name = "tags")
    @NotBlank (message = "El campo tags no puede estar vacío.")
    private String tags;

    @OneToMany (mappedBy = "image", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List <VariantEntity> variantEntities = new ArrayList<>();


    public ImageEntity(){
        //vacío para el framework
    }

    public ImageEntity(Integer idimagen, String id, String code, String campaign, int sequence, String set_, String tags, List<VariantEntity> variantEntities) {
        this.idimagen = idimagen;
        this.id = id;
        this.code = code;
        this.campaign = campaign;
        this.sequence = sequence;
        this.set_ = set_;
        this.tags = tags;
        this.variantEntities = variantEntities;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdimagen() {
        return idimagen;
    }

    public void setIdimagen(Integer id) {
        this.idimagen = id;
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
