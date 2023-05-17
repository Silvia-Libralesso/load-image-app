package com.nttdata.loadimageapp.repository;

import com.nttdata.loadimageapp.repository.dao.ImageDao;
import com.nttdata.loadimageapp.repository.dao.VariantDao;
import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.domain.repository.ImagePersistence;
import com.nttdata.loadimageapp.repository.entity.VariantEntity;
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
    private VariantDao variantDao;
    private ImgEntityImageMapper mapper;


    @Autowired
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

        ImageEntity imgE = this.imageDao.findById(id).get();
        return mapper.imgEntityToImage(imgE);

    }



    @Override
    public Image createImage(Image image) {

        ImageEntity imgE = mapper.imageToImgEntity(image);

        //para que se rellene el campo idimagen de variant cuando creo una imagen y a la vez una variante asociada
        List<VariantEntity> misvariantes = imgE.getVariants();
        misvariantes.forEach((i) -> {
            i.setImage(imgE);
        });
        
        this.imageDao.save(imgE);

        return mapper.imgEntityToImage(imgE);

    }

    @Override
    public Image updateImage(Image image) {

        //Optional<ImageEntity> imgEntity = this.imageDao.findById(image.getIdimgen());

        ImageEntity imagen = new ImageEntity();

        imagen.setIdimagen(image.getIdimgen());
        imagen.setId(image.getId());
        imagen.setCode(image.getCode());
        imagen.setCampaign(image.getCampaign());
        imagen.setSequence(image.getSequence());
        imagen.setSet_(image.getSet_());
        imagen.setTags(image.getTags());

        ImageEntity imgE = mapper.imageToImgEntity(image);
        List<VariantEntity> misvariantes = imgE.getVariants();
        misvariantes.forEach((i) -> {
            i.setImage(imgE);
        });
        //VariantEntity varE = this.variantDao.findById(image.)



        //guardar, update, variante

        //BeanUtils.copyProperties(imgEntity, imagen);

         return mapper.imgEntityToImage(this.imageDao.save(imagen));
    }

    @Override
    public void deleteImage(Integer id) {

        this.imageDao.deleteById(id);

    }
}
