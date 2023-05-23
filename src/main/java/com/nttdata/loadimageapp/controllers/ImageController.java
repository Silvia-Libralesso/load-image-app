package com.nttdata.loadimageapp.controllers;

import com.nttdata.loadimageapp.domain.service.ImageService;
import com.nttdata.loadimageapp.exceptions.ImageNotFoundException;
import com.nttdata.loadimageapp.exceptions.ServerException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.rowset.serial.SerialException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("images")
public class ImageController {


    private final ImageService imageService;
    private final static Logger logger = LoggerFactory.getLogger(ImageController.class);


    public ImageController (ImageService imageService) {this.imageService = imageService;}


    @GetMapping
    public ResponseEntity<List<ImageDTO>> listAllImage(){
        try{
            logger.debug("Imagen controller - listAllImage: {}", imageService.findImageAll());
            return ResponseEntity.ok(imageService.findImageAll());
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ImageDTO> getImage(@PathVariable("id") Integer id) {

        ImageDTO image = imageService.getImage(id);
        logger.debug("Imagen controller - getImage por id: {}", image);

    if(image != null){
        return new ResponseEntity<>(image, HttpStatus.OK);
    }else {
        throw new ImageNotFoundException();
        //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }
      //return image.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));
    }

    @PostMapping
    public  ResponseEntity <ImageDTO> createImage(@Valid @RequestBody ImageDTO image){

        logger.debug("Imagen controller - createImage - imagen que llega a través del body: {}", image);

        logger.debug("Imagen controller - createImage - las variantes (array): {}", image.getVariants());
        ImageDTO imageEntityCreate = imageService.createImage(image);
        if(imageEntityCreate == null){
            throw new ServerException();
        }
        else {
            logger.debug("Imagen controller - createImage tras mapping: {}", imageEntityCreate);
            return new ResponseEntity<>(imageEntityCreate, HttpStatus.CREATED);
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity <ImageDTO> updateImage(@PathVariable("id") Integer id, @Valid @RequestBody ImageDTO image) {

        logger.debug("Imagen controller - updateImage - imagen que llega a través del body: {}", image);
        image.setIdimagen(id);
        ImageDTO miimagen = imageService.updateImage(image);

        if(miimagen == null){
            throw new ServerException();
        }
        else {
            logger.debug("Imagen controller - updateImage - imagen actualizada: {}", miimagen);
            return new ResponseEntity<>(miimagen, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}")
    public String deleteImage(@PathVariable("id") Integer id) {

        imageService.deleteImage(id);
        return "Imagen borrada.";
    }


}
