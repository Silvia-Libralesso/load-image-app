/*package com.nttdata.loadimageapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.loadimageapp.controllers.ImageController;
import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.repository.ImagePersistenceImpl;
import com.nttdata.loadimageapp.service.ImageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ImageController.class)
public class ImageControllerTest {

    @MockBean
    private ImagePersistenceImpl imagePersistenceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateImage() throws Exception{
        Image image = new Image(1,"7d7de6e2e56d070ff47a71c41727ee59","4239702923","V2022",1, "003","main, test",null);
        mockMvc.perform(post("localhost:8080/images").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(image)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnImage() throws Exception {
        Integer id = 1;
        Image image = new Image(1,"7d7de6e2e56d070ff47a71c41727ee59","4239702923","V2022",1, "003","main, test",null);

        when(imagePersistenceImpl.readById(id)).thenReturn(image);
        mockMvc.perform(get("localhost:8080/images", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.idimagen").value(image.getIdimagen()))
                .andExpect(jsonPath("$.id").value(image.getId()))
                .andExpect(jsonPath("$.code").value(image.getCode()))
                .andExpect(jsonPath("$.campaign").value(image.getCampaign()))
                .andExpect(jsonPath("$.sequence").value(image.getSequence()))
                .andExpect(jsonPath("$.set_").value(image.getSet_()))
                .andExpect(jsonPath("$.tags").value(image.getTags()))
                .andExpect(jsonPath("$.variants").value(image.getVariants()))
                .andDo(print());
    }

    @Test
    void shouldReturnNotFoundImage() throws Exception {
        Integer id = 1;

        when(imagePersistenceImpl.readById(id)).thenReturn(null);
        mockMvc.perform(get("localhost:8080/images", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }


    @Test
    void shouldReturnListOfImages() throws Exception {
        List<Image> images = new ArrayList<>(
                Arrays.asList(new Image(1,"7d7de6e2e56d070ff47a71c41727ee59","4239702923","V2022",1, "003","main, test",null),
                        new Image(2,"d8dde6e2e56d070ff47a71c41727ee59","5239702923","I2022",1, "003","main",null),
                        new Image(3, "9as7de6e2e56d070ff47a71c41727ee59","6239702923","V2022",1, "003","main, test",null)));

        when(imagePersistenceImpl.findImageAll()).thenReturn(images);
        mockMvc.perform(get("localhost:8080/images"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(images.size()))
                .andDo(print());
    }

    @Test
    void shouldUpdateImage() throws Exception {
        Integer id = 1;

        Image image = new Image(id,"7d7de6e2e56d070ff47a71c41727ee59","4239702923","V2022",1, "003","main, test",null);
        Image updatedimage = new Image(id, "d8dde6e2e56d070ff47a71c41727ee59","5239702923","I2022",1, "003","main",null);

        when(imagePersistenceImpl.readById(id)).thenReturn(image);
       // when(imagePersistenceImpl.updateImage(any(Image.class)).thenReturn(updatedimage);

        mockMvc.perform(put("localhost:8080/images", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedimage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idimagen").value(updatedimage.getIdimagen()))
                .andExpect(jsonPath("$.id").value(updatedimage.getId()))
                .andExpect(jsonPath("$.code").value(updatedimage.getCode()))
                .andExpect(jsonPath("$.campaign").value(updatedimage.getCampaign()))
                .andExpect(jsonPath("$.sequence").value(updatedimage.getSequence()))
                .andExpect(jsonPath("$.set_").value(updatedimage.getSet_()))
                .andExpect(jsonPath("$.tags").value(updatedimage.getTags()))
                .andExpect(jsonPath("$.variants").value(updatedimage.getVariants()))
                .andDo(print());
    }




}
*/