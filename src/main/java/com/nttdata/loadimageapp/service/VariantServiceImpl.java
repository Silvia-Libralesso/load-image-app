package com.nttdata.loadimageapp.service;

import com.nttdata.loadimageapp.controllers.VariantDTO;
import com.nttdata.loadimageapp.domain.model.Variant;
import com.nttdata.loadimageapp.domain.repository.VariantPersistence;
import com.nttdata.loadimageapp.domain.service.VariantService;
import com.nttdata.loadimageapp.service.mapper.VariantVariantDTOMapper;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class VariantServiceImpl implements VariantService {


    private VariantPersistence variantPersistence;
    private VariantVariantDTOMapper mapper;


    @Autowired
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
    public VariantDTO getVariant(Integer id) {

        return mapper.variantToVariantDTO(variantPersistence.readById(id));   }

    @Override
    public VariantDTO createVariant(VariantDTO variantDTO) {

        Variant variant = mapper.variantDTOToVariant(variantDTO);
        return mapper.variantToVariantDTO(variantPersistence.createVariant(variant));
    }

    @Override
    public VariantDTO updateVariant(VariantDTO variantDTO) {

        Variant variant = mapper.variantDTOToVariant(variantDTO);
        variant.setId(variantDTO.getId());
        return mapper.variantToVariantDTO(variantPersistence.updateVariant(variant));
    }

    @Override
    public void deleteVariant(Integer id) {

        variantPersistence.deleteVariant(id);
    }


}