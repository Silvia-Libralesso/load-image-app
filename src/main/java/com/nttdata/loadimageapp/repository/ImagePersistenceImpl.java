package com.nttdata.loadimageapp.repository;


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
    public Image readById(Integer id) {
        Optional<ImageEntity> imageEntityOptional = this.imageDao.findById(id);
        logger.debug("Print image by Id: {}", imageEntityOptional);


        return imageEntityOptional.map(mapper::imgEntityToImage).orElse(null);
    }



    @Override
    public Image createImage(Image image) {

        logger.debug("Print imagen pasada por parámetro createImage: " + image);

        ImageEntity imgE = mapper.imageToImgEntity(image);

        logger.debug("Print imagen después de mapeo a Entity createImage: "+ imgE);
        logger.debug("Variantes imagePersistence:" + imgE.getVariants());

        //para que se rellene el campo idimagen de variant cuando creo una imagen y a la vez una variante asociada
        List<VariantEntity> misvariantes = imgE.getVariants();

        logger.debug("Print arraylist variantes imagePersistence: "+ misvariantes);

        misvariantes.forEach((i) -> {
            i.setImage(imgE);
        });
        
        this.imageDao.save(imgE);

        return mapper.imgEntityToImage(imgE);

    }

    @Override
    public Image updateImage(Image image) {

        //Optional<ImageEntity> imgEntity = this.imageDao.findById(image.getIdimgen());
        /*
        ImageEntity imagen = new ImageEntity();

        imagen.setIdimagen(image.getIdimagen());
        imagen.setId(image.getId());
        imagen.setCode(image.getCode());
        imagen.setCampaign(image.getCampaign());
        imagen.setSequence(image.getSequence());
        imagen.setSet_(image.getSet_());
        imagen.setTags(image.getTags());
        */
        ImageEntity imgE = mapper.imageToImgEntity(image);



        //imagen.setVariantEntities(imgE.getVariants());



        //VariantEntity varE = this.variantDao.findById(image.)



        //guardar, update, variante

        //BeanUtils.copyProperties(imgEntity, imagen);

         return mapper.imgEntityToImage(this.imageDao.save(imgE));
    }

    @Override
    public void deleteImage(Integer id) {

        this.imageDao.deleteById(id);

    }
}
