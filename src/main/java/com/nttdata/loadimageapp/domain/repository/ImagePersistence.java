package com.nttdata.loadimageapp.domain.repository;

import com.nttdata.loadimageapp.domain.model.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagePersistence {

    /*Image findById(Integer id);
    List<Image> findAll(); //o Stream<Image> findAll();
    Image save(Image image);
    void deleteById(Integer id);

    */

    List<Image> findImageAll();
    Image readById(Integer id);
    //Image getImage(Integer id);
    Image createImage(Image image);
    Image updateImage(Image image);
    void deleteImage(Integer id);




}
