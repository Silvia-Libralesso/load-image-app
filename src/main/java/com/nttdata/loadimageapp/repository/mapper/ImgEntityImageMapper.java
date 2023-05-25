package com.nttdata.loadimageapp.repository.mapper;

import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import com.nttdata.loadimageapp.domain.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ImgEntityImageMapper
{
    ImgEntityImageMapper INSTANCE = Mappers.getMapper(ImgEntityImageMapper.class);

    //@Mapping(source = "variantEntities", target = "variant")
    Image imgEntityToImage (ImageEntity imageEntity);

   // @Mapping(source = "variant", target = "variantEntities")
    ImageEntity imageToImgEntity (Image image);

    List<ImageEntity> mapListImgToImgEnt(List<Image> images);

    List<Image> mapListImgEntToImg(List<ImageEntity> images);

    default <T> T unwrapOptional(Optional<T> optional) {
        return optional.orElse(null);
    }

    default <T> Optional<T> wrap(T value) {
        return Optional.ofNullable(value);
    }


}
