package com.nttdata.loadimageapp.domain.repository;

import com.nttdata.loadimageapp.domain.model.Variant;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VariantPersistence {

    List<Variant> findVariantAll();
    Optional<Variant> readById(Integer id);
    //Image getImage(Integer id);
    Variant createVariant(Variant variant);
    Variant updateVariant(Variant variant);
    void deleteVariant(Integer id);

}
