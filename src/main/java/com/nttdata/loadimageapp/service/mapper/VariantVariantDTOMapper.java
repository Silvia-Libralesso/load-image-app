package com.nttdata.loadimageapp.service.mapper;

import com.nttdata.loadimageapp.controllers.ImageDTO;
import com.nttdata.loadimageapp.controllers.VariantDTO;
import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.domain.model.Variant;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface VariantVariantDTOMapper {

    VariantDTO variantToVariantDTO (Variant variant);
    Variant variantDTOToVariant (VariantDTO variantDTO);

    default <T> T unwrapOptional(Optional<T> optional) {
        return optional.orElse(null);
    }

    default <T> Optional<T> wrap(T value) {
        return Optional.ofNullable(value);
    }
}
