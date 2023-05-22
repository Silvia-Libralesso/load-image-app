package com.nttdata.loadimageapp.controllers;

import com.nttdata.loadimageapp.domain.model.Variant;
import com.nttdata.loadimageapp.domain.service.VariantService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("variants")
public class VariantController {

    private final VariantService variantService;

    private final static Logger logger = LoggerFactory.getLogger(VariantController.class);

    public VariantController (VariantService variantService) {this.variantService = variantService;}


    @GetMapping
    public List<VariantDTO> listAllVariant() {
        List<VariantDTO> variantEntities = variantService.findVariantAll();
        logger.debug("Variant controller - listAllVariant: {}", variantEntities);
        return variantEntities;
    }

    @GetMapping(value = "/{id}")
    public VariantDTO getVariant(@PathVariable("id") Integer id) {

        VariantDTO variant= variantService.getVariant(id);
        logger.debug("Variant controller - getVariant por id: {}", variant);
        return variant;
    }

    @PostMapping
    public VariantDTO createVariant(@Valid @RequestBody VariantDTO variant) {

        logger.debug("Variant controller - createVariant - variante que llega a través del body: {}", variant);

        logger.debug("Variant controller - createVariant - la imgen de la variante: {}", variant.getImage());
        VariantDTO variantEntityCreate = variantService.createVariant(variant);

        logger.debug("Variant controller - createVariant tras mapping: {}", variantEntityCreate);
        return variantEntityCreate;
    }

    @PutMapping(value = "/{id}")
    public VariantDTO updateVariant(@PathVariable("id") Integer id, @Valid @RequestBody VariantDTO variant) {

        logger.debug("Variant controller - updateVariant - variante que llega a través del body: {}", variant);
        variant.setId(id);
        variant = variantService.updateVariant(variant);

        logger.debug("Variant controller - updateVariant - variante actualizada: {}", variant);
        return variant;
    }

    @DeleteMapping(value = "/{id}")
    public String deleteVariant(@PathVariable("id") Integer id) {

        variantService.deleteVariant(id);
        return "Variante borrada.";
    }
}