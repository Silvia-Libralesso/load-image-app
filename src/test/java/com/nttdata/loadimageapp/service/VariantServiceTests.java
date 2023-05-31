package com.nttdata.loadimageapp.service;


import com.nttdata.loadimageapp.controllers.ImageDTO;
import com.nttdata.loadimageapp.controllers.ImgDTO;
import com.nttdata.loadimageapp.controllers.VariantDTO;
import com.nttdata.loadimageapp.domain.model.Variant;
import com.nttdata.loadimageapp.domain.repository.ImagePersistence;
import com.nttdata.loadimageapp.domain.repository.VariantPersistence;
import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import com.nttdata.loadimageapp.service.mapper.ImageImageDTOMapper;
import com.nttdata.loadimageapp.service.mapper.VariantVariantDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VariantServiceTests {

    @Mock
    private VariantPersistence variantPersistence;
    @Mock
    private VariantVariantDTOMapper mapper;

    @InjectMocks
    private VariantServiceImpl variantService;

    private VariantDTO variant;



    @BeforeEach
    void setup() {
        ImgDTO imagen = new ImgDTO(1, "7d7de6e2e56d070ff47a71c41727ee59");
        VariantDTO variante = new VariantDTO(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);
    }

    @DisplayName("Test para guardar una variante")
    @Test
    void testGuardarVariante(){

        ImgDTO imagen = new ImgDTO(1, "7d7de6e2e56d070ff47a71c41727ee59");
        VariantDTO variante = new VariantDTO(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);
        //given
        given(mapper.variantToVariantDTO(variantPersistence.createVariant(mapper.variantDTOToVariant(variante)))).willReturn(variante);

        //when
        VariantDTO varianteGuardada = variantService.createVariant(variante);

        //then
        assertThat(varianteGuardada).isNotNull();
    }

    @DisplayName("Test para listar las imágenes")
    @Test
    void testListarImagenes(){
        //given
        ImgDTO imagen = new ImgDTO(1, "7d7de6e2e56d070ff47a71c41727ee59");
        VariantDTO variante1 = new VariantDTO(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);
        VariantDTO variante2 = new VariantDTO(2, "front, test", "/2022/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1100, 700, "jpg", imagen);

        Variant variante3 = mapper.variantDTOToVariant(variante1);
        Variant variante4 = mapper.variantDTOToVariant(variante2);

        List<Variant> variantes = new ArrayList<>();
        variantes.add(variante3);
        variantes.add(variante4);

        //given
        given(variantPersistence.findVariantAll()).willReturn(variantes);

        //when
        List<VariantDTO> misvariantes = variantService.findVariantAll();

        //then
        assertThat(misvariantes).isNotNull();
        assertThat(misvariantes.size()).isEqualTo(2);
    }

    @DisplayName("Test para devolver una lista vacia")
    @Test
    void testListarColeccionVariantesVacia(){
        //given

        given(variantPersistence.findVariantAll()).willReturn(Collections.emptyList());

        //when
        List<VariantDTO> listaVariantes = variantService.findVariantAll();

        //then
        assertThat(listaVariantes).isEmpty();
        assertThat(listaVariantes.size()).isEqualTo(0);
    }

    @DisplayName("Test para obtener una variante por ID")
    @Test
    void testObtenerVariantePorId(){

        ImgDTO imagen = new ImgDTO(1, "7d7de6e2e56d070ff47a71c41727ee59");
        VariantDTO variante1 = new VariantDTO(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);
        //given
        given(variantPersistence.readById(1)).willReturn(Optional.of(mapper.variantDTOToVariant(variant)));

        //when
        VariantDTO varianteGuardada = variantService.getVariant(variante1.getId()).get();

        //then
        assertThat(varianteGuardada).isNotNull();
    }

    @DisplayName("Test para actualizar una variante")
    @Test
    void testActualizarVariante(){

        ImgDTO imagen = new ImgDTO(1, "7d7de6e2e56d070ff47a71c41727ee59");
        VariantDTO variante1 = new VariantDTO(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);
        //given
        given(mapper.variantToVariantDTO(variantPersistence.updateVariant(mapper.variantDTOToVariant(variante1)))).willReturn(variante1);
        variante1.setHeight(400);
        variante1.setTags("main");

        //when
        VariantDTO varianteActualizada  = variantService.updateVariant(variante1);

        //then
        assertThat(varianteActualizada.getHeight()).isEqualTo(400);
        assertThat(varianteActualizada.getTags()).isEqualTo("main");
    }


    @DisplayName("Test para eliminar una variante")
    @Test
    void testEliminarVariante(){
        //given
        Integer variantId = 1;
        willDoNothing().given(variantPersistence).deleteVariant(variantId);

        //when
        variantService.deleteVariant(variantId);

        //then
        verify(variantPersistence,times(1)).deleteVariant(variantId);
    }

}
