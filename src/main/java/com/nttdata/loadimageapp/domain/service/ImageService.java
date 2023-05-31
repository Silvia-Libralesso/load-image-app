package com.nttdata.loadimageapp.domain.service;

import com.nttdata.loadimageapp.controllers.ImageDTO;
import com.nttdata.loadimageapp.domain.model.Image;


import java.util.List;
import java.util.Optional;

public interface ImageService {

    List<ImageDTO> findImageAll();
    Optional <ImageDTO> getImage(Integer id);
    ImageDTO createImage(ImageDTO imageDTO);
    ImageDTO updateImage(ImageDTO imageDTO);
    void deleteImage(Integer id);

    //public Optional <ImageDTO> findByCode (String code);

}


