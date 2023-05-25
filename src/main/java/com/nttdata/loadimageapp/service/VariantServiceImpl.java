package com.nttdata.loadimageapp.service;

import com.nttdata.loadimageapp.controllers.VariantDTO;
import com.nttdata.loadimageapp.domain.model.Variant;
import com.nttdata.loadimageapp.domain.repository.VariantPersistence;
import com.nttdata.loadimageapp.domain.service.VariantService;
import com.nttdata.loadimageapp.service.mapper.VariantVariantDTOMapper;
import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VariantServiceImpl implements VariantService {


    private final VariantPersistence variantPersistence;
    private final VariantVariantDTOMapper mapper;

    private final static Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    public VariantServiceImpl (VariantPersistence variantPersistence, VariantVariantDTOMapper mapper){
        this.variantPersistence = variantPersistence;
        this.mapper = mapper;
    }

    @Override
    public List<VariantDTO> findVariantAll() {

        return variantPersistence.findVariantAll().stream().map(variant -> {
            VariantDTO variantDTO = mapper.variantToVariantDTO(variant);
            return variantDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional <VariantDTO> getVariant(Integer id) {
        logger.debug("Variante ServiceImpl- getVariant - id pasado por parámetro: {}", id);
        VariantDTO variantDTO = mapper.variantToVariantDTO(variantPersistence.readById(id).get());
        return mapper.wrap(variantDTO);
    }

    @Override
    public VariantDTO createVariant(VariantDTO variantDTO) {

        logger.debug("Variant ServiceImpl- createVariant - variante pasada por parámetro: {}", variantDTO);
        logger.debug("Variant ServiceImpl- createVariant - imagen de la variante pasada por parámetro: {}", variantDTO.getImage());

        Variant variant = mapper.variantDTOToVariant(variantDTO);
        logger.debug("Variant ServiceImpl- createVariant - tras mappeo: {}", variantDTO);

        return mapper.variantToVariantDTO(variantPersistence.createVariant(variant));
    }

    @Override
    public VariantDTO updateVariant(VariantDTO variantDTO) {

        logger.debug("Variant ServiceImpl- updateVariant - variante pasada por parámetro: {}", variantDTO);
        Variant variant = mapper.variantDTOToVariant(variantDTO);
        logger.debug("Variant ServiceImpl- updateVariant - variante tras mappeo a  image: {}", mapper.variantDTOToVariant(variantDTO));
        variant.setId(variantDTO.getId());
        return mapper.variantToVariantDTO(variantPersistence.updateVariant(variant));
    }

    @Override
    public void deleteVariant(Integer id) {

        logger.debug("Variante ServiceImpl- deleteVariant - id variant para borrar: {}", id);
        variantPersistence.deleteVariant(id);
    }


}