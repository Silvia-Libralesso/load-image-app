package com.nttdata.loadimageapp.repository;

import com.nttdata.loadimageapp.exceptions.ImageNotFoundException;
import com.nttdata.loadimageapp.exceptions.RequiredRequestBodyException;
import com.nttdata.loadimageapp.repository.dao.ImageDao;
import com.nttdata.loadimageapp.repository.dao.VariantDao;
import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.domain.repository.ImagePersistence;
import com.nttdata.loadimageapp.repository.entity.VariantEntity;
import com.nttdata.loadimageapp.repository.mapper.ImgEntityImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import java.util.stream.Collectors;

@Repository
public class ImagePersistenceImpl implements ImagePersistence {

    private final ImageDao imageDao;
    private final VariantDao variantDao;
    private final ImgEntityImageMapper mapper;

    private static Logger logger = LoggerFactory.getLogger(ImagePersistenceImpl.class);



    public ImagePersistenceImpl (ImageDao imageDao, ImgEntityImageMapper mapper, VariantDao variantDao){

        this.imageDao = imageDao;
        this.mapper = mapper;
        this.variantDao = variantDao;

    }



    @Override
    public List<Image> findImageAll() {

        return imageDao.findAll()
                .stream()
                .map(mapper::imgEntityToImage)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Image> readById(Integer id) {

        ImageEntity imageEntity = this.imageDao.findById(id).orElseThrow(() -> new ImageNotFoundException());

        logger.debug("Print id de la imagen: {} " , id);


        Image imagen = mapper.imgEntityToImage(imageEntity);

        return mapper.wrap(imagen);

        //return mapper.imageEntityToImage(imgE);

    }



    @Override
    public Image createImage(Image image) {



        if(Objects.isNull(image)){
            throw new RequiredRequestBodyException();
        }
        else {

        logger.debug("Print imagen pasada por parámetro createImage: {}" , image);

        ImageEntity imgE = mapper.imageToImgEntity(image);

        logger.debug("Print imagen después de mapeo a Entity createImage: {}", imgE);
        logger.debug("Variantes imagePersistence: {}", imgE.getVariants());

        //para que se rellene el campo idimagen de variant cuando creo una imagen y a la vez una variante asociada
        List<VariantEntity> misvariantes = imgE.getVariants();

        logger.debug("Print arraylist variantes imagePersistence: {}", misvariantes);

        misvariantes.forEach((i) -> {
            i.setImage(imgE);
        });


            this.imageDao.save(imgE);

            return mapper.imgEntityToImage(imgE);
        }

    }

    @Override
    public Image updateImage(Image image) {

        logger.debug("Print imagen pasada por parámetro updateImage: {}" , image);

        if(!this.imageDao.findById(image.getIdimagen()).isPresent()){
            throw new ImageNotFoundException();
        }
        else {

            ImageEntity imgE = mapper.imageToImgEntity(image);

            logger.debug("Print imagen después de mapeo a Entity updateImage: {}", imgE);

            return mapper.imgEntityToImage(this.imageDao.save(imgE));
        }

    }

    @Override
    public void deleteImage(Integer id) {

        logger.debug("Id a borrar (delete image): {} " , id);
        this.imageDao.deleteById(id);

    }

    @Override
    public Optional <Image> findByCode(String code){
        return imageDao.findByCode(code);
    }
}
