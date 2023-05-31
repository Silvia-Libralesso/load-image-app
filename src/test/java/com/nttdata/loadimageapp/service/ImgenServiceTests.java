package com.nttdata.loadimageapp.service;

import com.nttdata.loadimageapp.controllers.ImageDTO;

import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.domain.repository.ImagePersistence;

import com.nttdata.loadimageapp.service.mapper.ImageImageDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ImgenServiceTests {

    @Mock
    private ImagePersistence imagePersistence;
    @Mock
    private ImageImageDTOMapper mapper;

    @InjectMocks
    private ImageServiceImpl imageService;

    private ImageDTO imagen;



    @BeforeEach
    void setup() {
        ImageDTO imagen = new ImageDTO(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", null);
    }

    @DisplayName("Test para guardar una imagen")
    @Test
    void testGuardarImagen(){

        ImageDTO imagen = new ImageDTO(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", null);
        //given
        given(mapper.imageToImageDTO(imagePersistence.createImage(mapper.imageDTOToImage(imagen)))).willReturn(imagen);

        //when
        ImageDTO imagenGuardada = imageService.createImage(imagen);

        //then
        assertThat(imagenGuardada).isNotNull();
    }

    @DisplayName("Test para listar las imágenes")
    @Test
    void testListarImagenes(){
        //given
        ImageDTO imagen = new ImageDTO(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", null);
        ImageDTO imagen2 = new ImageDTO(2, "8d7de6e2e56d070ff47a71c41727ee59", "5239702923", "V2022", 1, "003", "main, test", null);

        Image imagen3 = mapper.imageDTOToImage(imagen);
        Image imagen4 = mapper.imageDTOToImage(imagen2);

        List<Image> imagenes = new ArrayList<>();
        imagenes.add(imagen3);
        imagenes.add(imagen4);


        given(imagePersistence.findImageAll()).willReturn(imagenes);

        //no funciona con List.of
        //given(imagePersistence.findImageAll()).willReturn(List.of(imagen3,imagen4));

        //when
        List<ImageDTO> misimagenes = imageService.findImageAll();

        //then
        assertThat(misimagenes).isNotNull();
        assertThat(misimagenes.size()).isEqualTo(2);
    }

    @DisplayName("Test para devolver una lista vacia")
    @Test
    void testListarColeccionImagenesVacia(){
        //given

        given(imagePersistence.findImageAll()).willReturn(Collections.emptyList());

        //when
        List<ImageDTO> listaImagenes = imageService.findImageAll();

        //then
        assertThat(listaImagenes).isEmpty();
        assertThat(listaImagenes.size()).isEqualTo(0);
    }

    @DisplayName("Test para obtener una imagen por ID")
    @Test
    void testObtenerImagenPorId(){

        ImageDTO imagen = new ImageDTO(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", null);
        //given
        given(imagePersistence.readById(1)).willReturn(Optional.of(mapper.imageDTOToImage(imagen)));
        //Mockito.when(imagePersistence.readById(imagen.getIdimagen()).thenReturn(Optional.of(mapper.imageDTOToImage(imagen))));

        //when
        ImageDTO imagenGuardada = imageService.getImage(imagen.getIdimagen()).get();

        //then
        assertThat(imagenGuardada).isNotNull();
    }

    @DisplayName("Test para actualizar una imagen")
    @Test
    void testActualizarImgen(){

        ImageDTO imagen = new ImageDTO(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", null);
        //given
        given(mapper.imageToImageDTO(imagePersistence.updateImage(mapper.imageDTOToImage(imagen)))).willReturn(imagen);
        imagen.setCode("6239702923");
        imagen.setTags("main");

        //when
        ImageDTO imagenActualizada  = imageService.updateImage(imagen);

        //then
        assertThat(imagenActualizada.getCode()).isEqualTo("6239702923");
        assertThat(imagenActualizada.getSet_()).isEqualTo("main");
    }


    @DisplayName("Test para eliminar una imagen")
    @Test
    void testEliminarEmpleado(){
        //given
        Integer imagenId = 1;
        willDoNothing().given(imagePersistence).deleteImage(imagenId);

        //when
        imageService.deleteImage(imagenId);

        //then
        verify(imagePersistence,times(1)).deleteImage(imagenId);
    }



}
