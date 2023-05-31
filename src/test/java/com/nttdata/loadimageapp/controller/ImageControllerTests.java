package com.nttdata.loadimageapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.loadimageapp.controllers.ImageDTO;
import com.nttdata.loadimageapp.domain.service.ImageService;
import com.nttdata.loadimageapp.domain.service.VariantService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ImageControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageService imageService;

    @MockBean
    private VariantService variantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGuardarImagen() throws Exception {
        //given
        ImageDTO imagen = new ImageDTO(3, "9d7de6e2e56d070ff47a71c41727ee59", "6239702923", "V2022", 1, "005", "main, test", null);
        given(imageService.createImage(any(ImageDTO.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when
        ResultActions response = mockMvc.perform(post("/images")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(imagen)));

        //then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(imagen.getId())))
                .andExpect(jsonPath("$.code",is(imagen.getCode())))
                .andExpect(jsonPath("$.tags",is(imagen.getTags())));
    }

    @Test
    void testListarImagenes() throws Exception{
        //given
        List<ImageDTO> listaImagenes = new ArrayList<>();
        listaImagenes.add(new ImageDTO(1, "9d7de6e2e56d070ff47a71c41727ee59", "6239702923", "V2022", 1, "005", "main, test", null));
        listaImagenes.add(new ImageDTO(2, "8d7de6e2e56d070ff47a71c41727ee59", "5239702923", "I2022", 2, "003", "main", null));
        listaImagenes.add(new ImageDTO(3, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "008", "main, test", null));
        listaImagenes.add(new ImageDTO(4, "6d7de6e2e56d070ff47a71c41727ee59", "3239702923", "I2022", 5, "007", "main", null));
        listaImagenes.add(new ImageDTO(5, "5d7de6e2e56d070ff47a71c41727ee59", "2239702923", "V2023", 6, "005", "main, test", null));
        given(imageService.findImageAll()).willReturn(listaImagenes);

        //when
        ResultActions response = mockMvc.perform(get("/images"));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(listaImagenes.size())));
    }

    @Test
    void testObtenerImagenPorId() throws Exception{
        //given
        int imagenId = 1;
        ImageDTO imagen = new ImageDTO(1, "9d7de6e2e56d070ff47a71c41727ee59", "6239702923", "V2022", 1, "005", "main, test", null);
        given(imageService.getImage(imagenId)).willReturn(Optional.of(imagen));

        //when
        ResultActions response = mockMvc.perform(get("/images/{id}",imagenId));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id",is(imagen.getId())))
                .andExpect(jsonPath("$.code",is(imagen.getCode())))
                .andExpect(jsonPath("$.tags",is(imagen.getTags())));
    }

    @Test
    void testObtenerImageNotFound() throws Exception{
        //given
        int imagenId = 1;
        ImageDTO imagen = new ImageDTO(1, "9d7de6e2e56d070ff47a71c41727ee59", "6239702923", "V2022", 1, "005", "main, test", null);
        given(imageService.getImage(imagenId)).willReturn(Optional.empty());

        //when
        ResultActions response = mockMvc.perform(get("/images/{id}",imagenId));

        //then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }


    @Test
    void testActualizarImagen() throws Exception{
        //given
        Integer imagenId = 1;
        ImageDTO imagenGuardada = new ImageDTO(1, "9d7de6e2e56d070ff47a71c41727ee59", "6239702923", "V2022", 1, "005", "main, test", null);

        ImageDTO imagenActualizada = new ImageDTO(2, "8d7de6e2e56d070ff47a71c41727ee59", "7239702923", "V2022", 1, "005", "main, test", null);

        given(imageService.getImage(imagenId)).willReturn(Optional.of(imagenGuardada));
        given(imageService.updateImage(any(ImageDTO.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when
        ResultActions response = mockMvc.perform(put("/images/{id}",imagenId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(imagenActualizada)));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id",is(imagenActualizada.getId())))
                .andExpect(jsonPath("$.code",is(imagenActualizada.getCode())))
                .andExpect(jsonPath("$.tags",is(imagenActualizada.getTags())));
    }


    @Test
    void testActualizarImagenNoEncontrada() throws Exception{
        //given
        Integer imagenId = 1;
        ImageDTO imagenGuardada = new ImageDTO(2, "9d7de6e2e56d070ff47a71c41727ee59", "6239702923", "V2022", 1, "005", "main, test", null);

        ImageDTO imagenActualizada = new ImageDTO(3, "8d7de6e2e56d070ff47a71c41727ee59", "7239702923", "V2022", 1, "005", "main, test", null);


        given(imageService.getImage(imagenId)).willReturn(Optional.empty());
        given(imageService.updateImage(any(ImageDTO.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when
        ResultActions response = mockMvc.perform(put("/images/{id}",imagenId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(imagenActualizada)));

        //then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testEliminarImagen() throws Exception{
        //given
        Integer imagenId = 1;
        willDoNothing().given(imageService).deleteImage(imagenId);

        //when
        ResultActions response = mockMvc.perform(delete("/images/{id}",imagenId));

        //then
        response.andExpect(status().isOk())
                .andDo(print());
    }

}
