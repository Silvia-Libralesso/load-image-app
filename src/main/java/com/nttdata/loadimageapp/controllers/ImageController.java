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

            logger.debug("Imagen controller - listAllImage: {}", imageService.findImageAll());
            return ResponseEntity.ok(imageService.findImageAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ImageDTO> getImage(@PathVariable("id") Integer id) {

        ImageDTO image = imageService.getImage(id).get();
        logger.debug("Imagen controller - getImage por id: {}", image);

        return new ResponseEntity<>(image, HttpStatus.OK);

      //return image.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));
    }

    @PostMapping
    public  ResponseEntity <ImageDTO> createImage(@Valid @RequestBody ImageDTO imageDto){

        logger.debug("Imagen controller - createImage - imagen que llega a través del body: {}", imageDto);

        logger.debug("Imagen controller - createImage - las variantes (array): {}", imageDto.getVariants());
        ImageDTO imageEntityCreate = imageService.createImage(imageDto);

            logger.debug("Imagen controller - createImage tras mapping: {}", imageEntityCreate);
            return new ResponseEntity<>(imageEntityCreate, HttpStatus.CREATED);


    }

    @PutMapping(value = "/{id}")
    public ResponseEntity <ImageDTO> updateImage(@PathVariable("id") Integer id, @Valid @RequestBody ImageDTO imageDto) {

        logger.debug("Imagen controller - updateImage - imagen que llega a través del body: {}", imageDto);
        imageDto.setIdimagen(id);
        ImageDTO miimagen = imageService.updateImage(imageDto);

            logger.debug("Imagen controller - updateImage - imagen actualizada: {}", miimagen);
            return new ResponseEntity<>(miimagen, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable("id") Integer id) {

        imageService.deleteImage(id);
        return  new ResponseEntity<>("Imagen borrada.", HttpStatus.OK);
    }


}
