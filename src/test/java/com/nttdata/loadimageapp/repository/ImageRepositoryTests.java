package com.nttdata.loadimageapp.repository;

import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.repository.dao.ImageDao;
import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class ImageRepositoryTests {

    @Autowired
    private ImageDao imageDao;

    private ImageEntity imagen;


    @BeforeEach
    void setup() {
        ImageEntity imagen = new ImageEntity(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", null);
    }


    @DisplayName("Test para guardar un imagen")
    @Test
    void testGuardarImagen() {
        //given
        ImageEntity imagen1 = new ImageEntity(2, "8d7de6e2e56d070ff47a71c41727ee59", "5239702923", "I2022", 1, "004", "main, test", null);

        //when - acción o el comportamiento que vamos a probar
        ImageEntity imagenGuardada = imageDao.save(imagen1);

        //then - verificar la salida
        assertThat(imagenGuardada).isNotNull();
        assertThat(imagenGuardada.getIdimagen()).isGreaterThan(0);
    }

    @DisplayName("Test para listar todas las imágenes")
    @Test
    void testListarImagenes() {
        //given
        ImageEntity imagen2 = new ImageEntity(3, "9d7de6e2e56d070ff47a71c41727ee59", "6239702923", "V2022", 1, "005", "main, test", null);
        ImageEntity imagen3 = new ImageEntity(4, "10d7de6e2e56d070ff47a71c41727ee59", "6239702923", "I2022", 1, "005", "main, test", null);
        imageDao.save(imagen2);
        imageDao.save(imagen3);

        //when
        List<ImageEntity> listaImagenes = imageDao.findAll();

        //then
        assertThat(listaImagenes).isNotNull();
        assertThat(listaImagenes.size()).isEqualTo(2);
    }

    @DisplayName("Test para obtener una imagen por ID")
    @Test
    void testObtenerImagenPorId(){

        ImageEntity imagen = new ImageEntity(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", null);
        imageDao.save(imagen);
        //when - comportamiento o accion que vamos a probar
        ImageEntity imageEntity = imageDao.findById(imagen.getIdimagen()).get();

        //then
        assertThat(imageEntity).isNotNull();
    }

    @DisplayName("Test para actualizar una imagen")
    @Test
    void testActualizarImagen(){

        ImageEntity imagen = new ImageEntity(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", null);
        imageDao.save(imagen);

        //when
        ImageEntity imagenGuardada = imageDao.findById(imagen.getIdimagen()).get();
        imagenGuardada.setId("10d7de6e2e56d070ff47a71c41727ee59");
        imagenGuardada.setCode("6239702923");
        imagenGuardada.setCampaign("V2022");
        imagenGuardada.setSequence(3);
        imagenGuardada.setSet_("005");
        imagenGuardada.setTags("main");
        imagenGuardada.setVariantEntities(null);

        ImageEntity imagenActualizada = imageDao.save(imagenGuardada);

        //then
        assertThat(imagenActualizada.getCode()).isEqualTo("6239702923");
        assertThat(imagenActualizada.getSet_()).isEqualTo("005");
    }

    @DisplayName("Test para eliminar una imagen")
    @Test
    void testEliminarImagen(){
        ImageEntity imagen = new ImageEntity(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", null);
        imageDao.save(imagen);

        //when
        imageDao.deleteById(imagen.getIdimagen());
        Optional<ImageEntity> imagenOptional = imageDao.findById(imagen.getIdimagen());

        //then
        assertThat(imagenOptional).isEmpty();
    }




}
