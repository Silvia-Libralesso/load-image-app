package com.nttdata.loadimageapp.controllers;

import com.nttdata.loadimageapp.domain.model.Variant;
import com.nttdata.loadimageapp.domain.service.VariantService;
import com.nttdata.loadimageapp.exceptions.ImageNotFoundException;
import com.nttdata.loadimageapp.exceptions.ServerException;
import com.nttdata.loadimageapp.exceptions.VariantNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("variants")
public class VariantController {

    private final VariantService variantService;

    private final static Logger logger = LoggerFactory.getLogger(VariantController.class);

    public VariantController (VariantService variantService) {this.variantService = variantService;}


    @GetMapping
    public ResponseEntity<List<VariantDTO>> listAllVariant() {
        try {
            logger.debug("Variant controller - listAllVariant: {}", variantService.findVariantAll());
            return ResponseEntity.ok(variantService.findVariantAll());
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VariantDTO> getVariant(@PathVariable("id") Integer id) {

        VariantDTO variant= variantService.getVariant(id).get();
        logger.debug("Variant controller - getVariant por id: {}", variant);
        if(variant != null){
            return new ResponseEntity<>(variant, HttpStatus.OK);
        }
        else{
            throw new VariantNotFoundException();
        }
    }

    @PostMapping
    public ResponseEntity <VariantDTO> createVariant(@Valid @RequestBody VariantDTO variant) {

        logger.debug("Variant controller - createVariant - variante que llega a través del body: {}", variant);

        logger.debug("Variant controller - createVariant - la imgen de la variante: {}", variant.getImage());
        VariantDTO variantEntityCreate = variantService.createVariant(variant);
        if(variantEntityCreate == null){
            throw new ServerException();
        }
        else {
            logger.debug("Variant controller - createVariant tras mapping: {}", variantEntityCreate);
            return new ResponseEntity<>(variantEntityCreate, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VariantDTO> updateVariant(@PathVariable("id") Integer id, @Valid @RequestBody VariantDTO variant) {

        logger.debug("Variant controller - updateVariant - variante que llega a través del body: {}", variant);
        variant.setId(id);
        VariantDTO mivariant = variantService.updateVariant(variant);

        if(mivariant == null){
            throw new ServerException();
        }
        else {
            logger.debug("Variant controller - updateVariant - variante actualizada: {}", variant);
            return new ResponseEntity<>(mivariant, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}")
    public String deleteVariant(@PathVariable("id") Integer id) {

        variantService.deleteVariant(id);
        return "Variante borrada.";
    }
}