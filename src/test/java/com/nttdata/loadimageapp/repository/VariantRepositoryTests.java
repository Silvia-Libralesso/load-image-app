package com.nttdata.loadimageapp.repository;

import com.nttdata.loadimageapp.repository.dao.ImageDao;
import com.nttdata.loadimageapp.repository.dao.VariantDao;
import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import com.nttdata.loadimageapp.repository.entity.VariantEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class VariantRepositoryTests {

    @Autowired
    private VariantDao variantDao;

    private VariantEntity variant;


    @BeforeEach
    void setup() {
        ImageEntity imagen = new ImageEntity(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", new ArrayList<>());
        VariantEntity variant = new VariantEntity(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);
    }


    @DisplayName("Test para guardar un Variante")
    @Test
    void testGuardarVariante() {
        //given
        ImageEntity imagen = new ImageEntity(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", new ArrayList<>());
        VariantEntity variant = new VariantEntity(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);

        //when - acción o el comportamiento que vamos a probar
        VariantEntity varianteGuardada = variantDao.save(variant);

        //then - verificar la salida
        assertThat(varianteGuardada).isNotNull();
        assertThat(varianteGuardada.getId()).isGreaterThan(0);
    }

    @DisplayName("Test para listar todas las variantes")
    @Test
    void testListarImagenes() {
        //given
        ImageEntity imagen = new ImageEntity(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", new ArrayList<>());
        VariantEntity variant = new VariantEntity(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);
        VariantEntity variant1 = new VariantEntity(2, "front, test", "/2022/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123150", 1000, 400, "jpg", imagen);
        variantDao.save(variant);
        variantDao.save(variant1);

        //when
        List<VariantEntity> listaVariantes = variantDao.findAll();

        //then
        assertThat(listaVariantes).isNotNull();
        assertThat(listaVariantes.size()).isEqualTo(2);
    }

    @DisplayName("Test para obtener una variante por ID")
    @Test
    void testObtenerVariantePorId(){

        ImageEntity imagen = new ImageEntity(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", new ArrayList<>());
        VariantEntity variant = new VariantEntity(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);
        variantDao.save(variant);
        //when - comportamiento o accion que vamos a probar
        VariantEntity variantEntity = variantDao.findById(variant.getId()).get();

        //then
        assertThat(variantEntity).isNotNull();
    }

    @DisplayName("Test para actualizar una variante")
    @Test
    void testActualizarVariante(){

        ImageEntity imagen = new ImageEntity(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", new ArrayList<>());
        VariantEntity variant = new VariantEntity(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);
        variantDao.save(variant);

        //when
        VariantEntity varianteGuardada = variantDao.findById(variant.getId()).get();
        varianteGuardada.setTags("front, test");
        varianteGuardada.setRelativePath("/2022/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132");
        varianteGuardada.setHeight(1300);
        varianteGuardada.setWidth(700);
        varianteGuardada.setExtension("png");
        varianteGuardada.setImage(imagen);

        VariantEntity varianteActualizada = variantDao.save(varianteGuardada);

        //then
        assertThat(varianteActualizada.getTags()).isEqualTo("front, test");
        assertThat(varianteActualizada.getRelativePath()).isEqualTo("/2022/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132");
    }

    @DisplayName("Test para eliminar una variante")
    @Test
    void testEliminarImagen(){

        ImageEntity imagen = new ImageEntity(1, "7d7de6e2e56d070ff47a71c41727ee59", "4239702923", "V2022", 1, "003", "main, test", new ArrayList<>());
        VariantEntity variant = new VariantEntity(1, "front, test, ppp", "/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132", 1200, 600, "jpg", imagen);
        variantDao.save(variant);

        //when
        variantDao.deleteById(variant.getId());
        Optional<VariantEntity> varianteOptional = variantDao.findById(variant.getId());

        //then
        assertThat(varianteOptional).isEmpty();
    }

}
