package com.nttdata.loadimageapp.repository.dao;


import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ImageDao extends JpaRepository<ImageEntity, Integer> {

    public Optional<Image> findByCode(String code);
}
