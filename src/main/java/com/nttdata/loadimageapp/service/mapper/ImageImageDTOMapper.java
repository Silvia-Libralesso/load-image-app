package com.nttdata.loadimageapp.service.mapper;

import com.nttdata.loadimageapp.controllers.ImageDTO;
import com.nttdata.loadimageapp.domain.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageImageDTOMapper {

    ImageDTO imageToImageDTO (Image image);
    Image imageDTOToImage (ImageDTO imageDTO);


}
