package com.nttdata.loadimageapp.domain.service;

import com.nttdata.loadimageapp.controllers.ImageDTO;
import com.nttdata.loadimageapp.domain.model.Image;


import java.util.List;

public interface ImageService {

    List<ImageDTO> findImageAll();
    ImageDTO getImage(Integer id);
    ImageDTO createImage(ImageDTO imageDTO);
    ImageDTO updateImage(ImageDTO imageDTO);
    void deleteImage(Integer id);


}


