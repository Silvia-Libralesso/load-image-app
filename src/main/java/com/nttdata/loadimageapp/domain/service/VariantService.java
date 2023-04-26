package com.nttdata.loadimageapp.domain.service;

import com.nttdata.loadimageapp.controllers.VariantDTO;
import com.nttdata.loadimageapp.domain.model.Variant;


import java.util.List;


public interface VariantService {



     List<VariantDTO> findVariantAll();
     VariantDTO getVariant(Integer id);
     VariantDTO createVariant(VariantDTO variant);
     VariantDTO updateVariant(VariantDTO variant);
     void deleteVariant(Integer id);

}