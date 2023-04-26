package com.nttdata.loadimageapp.repository;
import com.nttdata.loadimageapp.repository.dao.VariantDao;
import com.nttdata.loadimageapp.domain.model.Variant;
import com.nttdata.loadimageapp.repository.entity.VariantEntity;
import com.nttdata.loadimageapp.domain.repository.VariantPersistence;
import com.nttdata.loadimageapp.repository.mapper.VariantEntityVariantMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class VariantPersistenceImpl implements VariantPersistence {


    private VariantDao variantDao;
    private VariantEntityVariantMapper mapper;


    @Autowired
    public VariantPersistenceImpl (VariantDao variantDao, VariantEntityVariantMapper mapper){

        this.variantDao = variantDao;
        this.mapper = mapper;
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
        this.variantDao.save(varE);
        return mapper.variantEntityToVariant(varE);
    }

    @Override
    public Variant updateVariant(Variant variant) {

        Optional<VariantEntity> varEntity = this.variantDao.findById(variant.getId());

        VariantEntity variante = new VariantEntity();
        BeanUtils.copyProperties(varEntity, variante);

        return mapper.variantEntityToVariant(this.variantDao.save(variante));
    }

    @Override
    public void deleteVariant(Integer id) {

        this.variantDao.deleteById(id);

    }
}