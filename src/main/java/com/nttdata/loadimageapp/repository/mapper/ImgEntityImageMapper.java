package com.nttdata.loadimageapp.repository.mapper;

import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import com.nttdata.loadimageapp.domain.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImgEntityImageMapper
{
    //@Mapping(source = "variantEntities", target = "variant")
    Image imgEntityToImage (ImageEntity imageEntity);

   // @Mapping(source = "variant", target = "variantEntities")
    ImageEntity imageToImgEntity (Image image);

    List<ImageEntity> mapListImgToImgEnt(List<Image> images);

    List<Image> mapListImgEntToImg(List<ImageEntity> images);

}
