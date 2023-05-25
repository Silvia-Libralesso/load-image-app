package com.nttdata.loadimageapp.domain.repository;

import com.nttdata.loadimageapp.domain.model.Image;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagePersistence {

    List<Image> findImageAll();
    Optional <Image> readById(Integer id);
    //Image getImage(Integer id);
    Image createImage(Image image);
    Image updateImage(Image image);
    void deleteImage(Integer id);




}
