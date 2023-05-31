package com.nttdata.loadimageapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.loadimageapp.controllers.ImageDTO;
import com.nttdata.loadimageapp.controllers.ImgDTO;
import com.nttdata.loadimageapp.controllers.VariantDTO;
import com.nttdata.loadimageapp.domain.service.ImageService;
import com.nttdata.loadimageapp.domain.service.VariantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class VariantControllerTests {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ImageService imageService;



    @MockBean
    private VariantService variantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGuardarVariante() throws Exception {
        //given
        ImgDTO imagen = new ImgDTO(1, "7d7de6e2e56d070ff47a71c41727ee59");
        VariantDTO variante = new VariantDTO(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);
        given(variantService.createVariant(any(VariantDTO.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when
        ResultActions response = mockMvc.perform(post("/variants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(variante)));

        //then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(variante.getId())))
                .andExpect(jsonPath("$.width",is(variante.getWidth())))
                .andExpect(jsonPath("$.tags",is(variante.getTags())));
    }

    @Test
    void testListarVariantes() throws Exception{
        //given
        ImgDTO imagen = new ImgDTO(1, "7d7de6e2e56d070ff47a71c41727ee59");
        List<VariantDTO> listaVariantes = new ArrayList<>();
        listaVariantes.add(new VariantDTO(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen));
        listaVariantes.add(new VariantDTO(2, "front, test, ppp", "/2022/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=1231231231585", 1300, 700, "jpg", imagen));
        listaVariantes.add(new VariantDTO(3, "front", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123858126", 800, 300, "jpg", imagen));

        given(variantService.findVariantAll()).willReturn(listaVariantes);

        //when
        ResultActions response = mockMvc.perform(get("/variants"));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(listaVariantes.size())));
    }

    @Test
    void testObtenerVariantePorId() throws Exception{
        //given
        int variantId = 1;
        ImgDTO imagen = new ImgDTO(1, "7d7de6e2e56d070ff47a71c41727ee59");
        VariantDTO variante = new VariantDTO(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);
        given(variantService.getVariant(variantId)).willReturn(Optional.of(variante));

        //when
        ResultActions response = mockMvc.perform(get("/images/{id}",variantId));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id",is(variante.getId())))
                .andExpect(jsonPath("$.width",is(variante.getWidth())))
                .andExpect(jsonPath("$.tags",is(variante.getTags())));
    }

    @Test
    void testObtenerVariantNotFound() throws Exception{
        //given
        int variantId = 1;
        ImgDTO imagen = new ImgDTO(1, "7d7de6e2e56d070ff47a71c41727ee59");
        VariantDTO variante = new VariantDTO(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);
        given(variantService.getVariant(variantId)).willReturn(Optional.empty());

        //when
        ResultActions response = mockMvc.perform(get("/variants/{id}",variantId));

        //then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }


    @Test
    void testActualizarVariante() throws Exception{
        //given
        int varianteId = 1;
        ImgDTO imagen = new ImgDTO(1, "7d7de6e2e56d070ff47a71c41727ee59");
        VariantDTO varianteGuardada= new VariantDTO(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);

        VariantDTO varianteActualizada = new VariantDTO(2, "front, test", "/2022/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1300, 700, "jpg", imagen);

        given(variantService.getVariant(varianteId)).willReturn(Optional.of(varianteGuardada));
        given(variantService.updateVariant(any(VariantDTO.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when
        ResultActions response = mockMvc.perform(put("/variants/{id}",varianteId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(varianteActualizada)));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.extension",is(varianteActualizada.getExtension())))
                .andExpect(jsonPath("$.width",is(varianteActualizada.getWidth())))
                .andExpect(jsonPath("$.tags",is(varianteActualizada.getTags())));
    }


    @Test
    void testActualizarVarianteNoEncontrada() throws Exception{
        //given
        Integer varianteId = 1;
        ImgDTO imagen = new ImgDTO(1, "7d7de6e2e56d070ff47a71c41727ee59");
        VariantDTO varianteGuardada= new VariantDTO(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);

        VariantDTO varianteActualizada = new VariantDTO(2, "front, test", "/2022/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1300, 700, "jpg", imagen);


        given(variantService.getVariant(varianteId)).willReturn(Optional.empty());
        given(variantService.updateVariant(any(VariantDTO.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when
        ResultActions response = mockMvc.perform(put("/variants/{id}",varianteId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(varianteActualizada)));

        //then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testEliminarVariante() throws Exception{
        //given
        Integer varianteId = 1;
        willDoNothing().given(variantService).deleteVariant(varianteId);

        //when
        ResultActions response = mockMvc.perform(delete("/variants/{id}",varianteId));

        //then
        response.andExpect(status().isOk())
                .andDo(print());
    }


}
