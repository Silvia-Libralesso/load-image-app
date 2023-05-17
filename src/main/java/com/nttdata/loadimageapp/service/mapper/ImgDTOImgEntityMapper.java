package com.nttdata.loadimageapp.service.mapper;

import com.nttdata.loadimageapp.controllers.ImageDTO;
import com.nttdata.loadimageapp.controllers.ImgDTO;
import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImgDTOImgEntityMapper {

    ImgDTO imgEntityToImgDTO (ImageEntity imageEntity);
    ImageEntity imgDTOToImgEntity (ImgDTO imgDTO);


}