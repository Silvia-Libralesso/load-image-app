package com.nttdata.loadimageapp.repository.mapper;


import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.domain.model.Variant;
import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import com.nttdata.loadimageapp.repository.entity.VariantEntity;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface VariantEntityVariantMapper {
    Variant variantEntityToVariant (VariantEntity variantEntity);
    VariantEntity variantToVariantEntity (Variant variant);

    default <T> T unwrapOptional(Optional<T> optional) {
        return optional.orElse(null);
    }

    default <T> Optional<T> wrap(T value) {
        return Optional.ofNullable(value);
    }

}
