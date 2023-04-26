package com.nttdata.loadimageapp.repository;

import com.nttdata.loadimageapp.repository.dao.ImageDao;
import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.domain.repository.ImagePersistence;
import com.nttdata.loadimageapp.repository.mapper.ImgEntityImageMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ImagePersistenceImpl implements ImagePersistence {

    private ImageDao imageDao;
    private ImgEntityImageMapper mapper;


    @Autowired
    public ImagePersistenceImpl (ImageDao imageDao, ImgEntityImageMapper mapper){

        this.imageDao = imageDao;
        this.mapper = mapper;
    }



    @Override
    public List<Image> findImageAll() {
        /*List<ImageEntity> images = this.imageDao.findAll();

        List<Image> misimagenes = new ArrayList<>();

        for(ImageEntity img: images){
            mapper.imgEntityToImage(img);
        }

        return misimagenes;

        */

        return imageDao.findAll()
                .stream()
                .map(mapper::imgEntityToImage)
                .collect(Collectors.toList());


    }

    @Override
    public Image readById(Integer id) {

        ImageEntity imgE = this.imageDao.findById(id).get();
        return mapper.imgEntityToImage(imgE);

    }



    @Override
    public Image createImage(Image image) {

        ImageEntity imgE = mapper.imageToImgEntity(image);
        this.imageDao.save(imgE);
        return mapper.imgEntityToImage(imgE);

    }

    @Override
    public Image updateImage(Image image) {

        Optional<ImageEntity> imgEntity = this.imageDao.findById(image.getIdImage());

        ImageEntity imagen = new ImageEntity();
        BeanUtils.copyProperties(imgEntity, imagen);

         return mapper.imgEntityToImage(this.imageDao.save(imagen));
    }

    @Override
    public void deleteImage(Integer id) {

        this.imageDao.deleteById(id);

    }
}
