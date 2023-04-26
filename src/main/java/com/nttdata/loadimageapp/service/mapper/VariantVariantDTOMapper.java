package com.nttdata.loadimageapp.service.mapper;

import com.nttdata.loadimageapp.controllers.ImageDTO;
import com.nttdata.loadimageapp.controllers.VariantDTO;
import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.domain.model.Variant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VariantVariantDTOMapper {

    VariantDTO variantToVariantDTO (Variant variant);
    Variant variantDTOToVariant (VariantDTO variantDTO);

}
