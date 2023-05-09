package com.nttdata.loadimageapp.repository.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.nttdata.loadimageapp.domain.model.Variant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;


@Entity
@Table (name="variant")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VariantEntity {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private Integer id;

    @Column (name="tags")
    @NotBlank(message = "El campo tags no puede estar vacío.")
    private String tags;

    @Column (name="relative_path")
    @NotBlank (message = "El campo relativePath no puede estar vacío.")
    private String relative_path;

    @Column (name="width")
    @NotNull(message = "El campo width no puede ser nulo.")
    private int width;

    @Column (name="height")
    @NotNull (message = "El campo height no puede ser nulo.")
    private int height;

    @Column (name="extension")
    @NotBlank (message = "El campo extension no puede estar vacío.")
    private String extension;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    public VariantEntity(){

    }


    public VariantEntity(Integer id, String tags, String relativePath, int width, int height, String extension, ImageEntity image) {
        this.id = id;
        this.tags = tags;
        this.relative_path = relativePath;
        this.width = width;
        this.height = height;
        this.extension = extension;
        this.image = image;
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
        return relative_path;
    }

    public void setRelativePath(String relativePath) {
        this.relative_path = relativePath;
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

    //@JsonBackReference
    public ImageEntity getImage() {
        return this.image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }
}
