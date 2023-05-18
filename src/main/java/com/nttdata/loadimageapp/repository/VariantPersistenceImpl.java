package com.nttdata.loadimageapp.repository;
import com.nttdata.loadimageapp.repository.dao.ImageDao;
import com.nttdata.loadimageapp.repository.dao.VariantDao;
import com.nttdata.loadimageapp.domain.model.Variant;
import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import com.nttdata.loadimageapp.repository.entity.VariantEntity;
import com.nttdata.loadimageapp.domain.repository.VariantPersistence;
import com.nttdata.loadimageapp.repository.mapper.VariantEntityVariantMapper;
import com.nttdata.loadimageapp.service.mapper.ImgDTOImgEntityMapper;
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

    public VariantPersistenceImpl (VariantDao variantDao, VariantEntityVariantMapper mapper, ImageDao imageDao){

        this.variantDao = variantDao;
        this.mapper = mapper;
        this.imageDao = imageDao;
    }


    @Override
    public List<Variant> findVariantAll() {
     return variantDao.findAll().stream().map(mapper::variantEntityToVariant).collect(Collectors.toList());
    }

    @Override
    public Variant readById(Integer id) {

        VariantEntity varE = this.variantDao.findById(id).get();
        return mapper.variantEntityToVariant(varE);
    }



    @Override
    public Variant createVariant(Variant variant) {
        VariantEntity varE = mapper.variantToVariantEntity(variant);
        varE.getImage().setId(variant.getImage().getId());


        this.variantDao.save(varE);
        return mapper.variantEntityToVariant(varE);
    }

    @Override
    public Variant updateVariant(Variant variant) {

        //Optional<VariantEntity> varEntity = this.variantDao.findById(variant.getId());
        /*
        VariantEntity variante = new VariantEntity();
        variante.setId(variant.getId());
        variante.setTags(variant.getTags());
        variante.setRelativePath(variant.getRelativePath());
        variante.setWidth(variant.getWidth());
        variante.setHeight(variant.getHeight());
        variante.setExtension(variant.getExtension());
        */

        VariantEntity variante = mapper.variantToVariantEntity(variant);
        ImageEntity imgE = this.imageDao.findById(variant.getImage().getIdimagen()).get();
        variante.setImage(imgE);

        //BeanUtils.copyProperties(varEntity, variante);

        return mapper.variantEntityToVariant(this.variantDao.save(variante));
    }

    @Override
    public void deleteVariant(Integer id) {

        this.variantDao.deleteById(id);

    }
}