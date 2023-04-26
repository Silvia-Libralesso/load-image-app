package com.nttdata.loadimageapp.repository.dao;


import com.nttdata.loadimageapp.repository.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;




public interface ImageDao extends JpaRepository<ImageEntity, Integer> {


}
