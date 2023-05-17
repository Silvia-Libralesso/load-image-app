package com.nttdata.loadimageapp.repository.mapper;

import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import com.nttdata.loadimageapp.domain.model.Image;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImgEntityImageMapper
{
    Image imgEntityToImage (ImageEntity imageEntity);
    ImageEntity imageToImgEntity (Image image);

    List<ImageEntity> mapListImgToImgEnt(List<Image> images);

    List<Image> mapListImgEntToImg(List<ImageEntity> images);

}
