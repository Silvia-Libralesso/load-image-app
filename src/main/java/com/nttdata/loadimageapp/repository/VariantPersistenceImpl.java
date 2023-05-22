package com.nttdata.loadimageapp.repository;
import com.nttdata.loadimageapp.repository.dao.ImageDao;
import com.nttdata.loadimageapp.repository.dao.VariantDao;
import com.nttdata.loadimageapp.domain.model.Variant;
import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import com.nttdata.loadimageapp.repository.entity.VariantEntity;
import com.nttdata.loadimageapp.domain.repository.VariantPersistence;
import com.nttdata.loadimageapp.repository.mapper.VariantEntityVariantMapper;
import com.nttdata.loadimageapp.service.mapper.ImgDTOImgEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class VariantPersistenceImpl implements VariantPersistence {


    private final VariantDao variantDao;
    private final ImageDao imageDao;
    private final VariantEntityVariantMapper mapper;

    private static Logger logger = LoggerFactory.getLogger(VariantPersistenceImpl.class);

    public VariantPersistenceImpl (VariantDao variantDao, VariantEntityVariantMapper mapper, ImageDao imageDao){

        this.variantDao = variantDao;
        this.mapper = mapper;
        this.imageDao = imageDao;
    }


    @Override
    public List<Variant> findVariantAll() {
     return variantDao.findAll()
             .stream()
             .map(mapper::variantEntityToVariant)
             .collect(Collectors.toList());
    }

    @Override
    public Variant readById(Integer id) {

        VariantEntity varE = this.variantDao.findById(id).get();

        logger.debug("Print id de la variante: {} " , id);
        logger.debug("Print variante por id: {} " , mapper.variantEntityToVariant(varE));

        return mapper.variantEntityToVariant(varE);
    }



    @Override
    public Variant createVariant(Variant variant) {

        logger.debug("Print variante pasada por parámetro createVariant: {}" , variant);

        VariantEntity varE = mapper.variantToVariantEntity(variant);

        logger.debug("Print variantee después de mapeo a Entity createVariant: {}", varE);
        logger.debug("Imagen de la variante: {}", varE.getImage());

        varE.getImage().setId(variant.getImage().getId());


        this.variantDao.save(varE);
        return mapper.variantEntityToVariant(varE);
    }

    @Override
    public Variant updateVariant(Variant variant) {

        logger.debug("Print variante pasada por parámetro updateVariant: {}" , variant);



        VariantEntity variante = mapper.variantToVariantEntity(variant);
        logger.debug("Print variante después de mapeo a Entity updateVariant: {}", variante);

        ImageEntity imgE = this.imageDao.findById(variant.getImage().getIdimagen()).get();
        variante.setImage(imgE);

        //BeanUtils.copyProperties(varEntity, variante);

        return mapper.variantEntityToVariant(this.variantDao.save(variante));
    }

    @Override
    public void deleteVariant(Integer id) {

        logger.debug("Id a borrar (delete variant): {} " , id);
        this.variantDao.deleteById(id);

    }
}