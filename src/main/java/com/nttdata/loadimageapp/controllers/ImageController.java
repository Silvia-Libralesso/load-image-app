package com.nttdata.loadimageapp.controllers;

import com.nttdata.loadimageapp.domain.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("images")
public class ImageController {


    private ImageService imageService;

    @Autowired
    public ImageController (ImageService imageService) {this.imageService = imageService;}


    @GetMapping
    public List<ImageDTO> listAllImage(){
        List<ImageDTO> imageEntities = imageService.findImageAll();

        return imageEntities;
    }

    @GetMapping(value = "/{id}")
    public ImageDTO getImage(@PathVariable("id") Integer id) {

        ImageDTO image = imageService.getImage(id);

       return image;
    }

    @PostMapping
    public ImageDTO createImage(@Valid @RequestBody ImageDTO image){

        ImageDTO imageEntityCreate = imageService.createImage(image);
        return imageEntityCreate;
    }

    @PutMapping(value = "/{id}")
    public ImageDTO updateImage(@PathVariable("id") Integer id, @Valid @RequestBody ImageDTO image) {

        image.setIdImage(id);
        image = imageService.updateImage(image);
        return image;
    }

    @DeleteMapping(value = "/{id}")
    public String deleteImage(@PathVariable("id") Integer id) {

        imageService.deleteImage(id);
        return "Imagen borrada.";
    }


}
