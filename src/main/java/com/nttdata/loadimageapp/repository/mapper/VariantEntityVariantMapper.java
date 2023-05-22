package com.nttdata.loadimageapp.repository.mapper;


import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.domain.model.Variant;
import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import com.nttdata.loadimageapp.repository.entity.VariantEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VariantEntityVariantMapper {
    Variant variantEntityToVariant (VariantEntity variantEntity);
    VariantEntity variantToVariantEntity (Variant variant);

}
