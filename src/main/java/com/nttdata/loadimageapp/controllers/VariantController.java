package com.nttdata.loadimageapp.controllers;

import com.nttdata.loadimageapp.domain.model.Variant;
import com.nttdata.loadimageapp.domain.service.VariantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("variants")
public class VariantController {

    private VariantService variantService;

    @Autowired
    public VariantController (VariantService variantService) {this.variantService = variantService;}


    @GetMapping
    public List<VariantDTO> listAllVariant() {
        List<VariantDTO> variantEntities = variantService.findVariantAll();

        return variantEntities;
    }

    @GetMapping(value = "/{id}")
    public VariantDTO getVariant(@PathVariable("id") Integer id) {

        VariantDTO variant= variantService.getVariant(id);

        return variant;
    }

    @PostMapping
    public VariantDTO createVariant(@Valid @RequestBody VariantDTO variant) {

        VariantDTO variantEntityCreate = variantService.createVariant(variant);

        return variantEntityCreate;
    }

    @PutMapping(value = "/{id}")
    public VariantDTO updateVariant(@PathVariable("id") Integer id, @Valid @RequestBody VariantDTO variant) {

        variant.setId(id);
        variant = variantService.updateVariant(variant);
        return variant;
    }

    @DeleteMapping(value = "/{id}")
    public String deleteVariant(@PathVariable("id") Integer id) {

        variantService.deleteVariant(id);
        return "Variante borrada.";
    }
}