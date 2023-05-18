package com.nttdata.loadimageapp.service;

import com.nttdata.loadimageapp.controllers.ImageController;
import com.nttdata.loadimageapp.controllers.ImageDTO;
import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.domain.repository.ImagePersistence;
import com.nttdata.loadimageapp.domain.service.ImageService;
import com.nttdata.loadimageapp.service.mapper.ImageImageDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImagePersistence imagePersistence;
    private final ImageImageDTOMapper mapper;

    private static Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    public ImageServiceImpl (ImagePersistence imagePersistence, ImageImageDTOMapper mapper) {
        this.imagePersistence = imagePersistence;
        this.mapper = mapper;
    }

    @Override
    public List<ImageDTO> findImageAll() {

        return imagePersistence.findImageAll()
                .stream()
                .map( image -> {
                    ImageDTO imageDTO = mapper.imageToImageDTO(image);
                    return imageDTO;
                }).collect(Collectors.toList());

    }

    @Override
    public ImageDTO getImage(Integer id) {
    return mapper.imageToImageDTO(imagePersistence.readById(id));

    }

    @Override
    public ImageDTO createImage(ImageDTO imageDTO) {

        logger.debug("Imagen ServiceImpl- createImage - imagen pasada por parámetro: "+ imageDTO);
        logger.debug("Imagen ServiceImpl- createImage - array variantes imagen pasada por parámetro: " + imageDTO.getVariants());

        Image image = mapper.imageDTOToImage(imageDTO);
        logger.debug("Imagen ServiceImpl- createImage - tras mapeo: " + image);



        return mapper.imageToImageDTO(imagePersistence.createImage(image));

    }

    @Override
    public ImageDTO updateImage(ImageDTO imageDTO) {

        Image image = mapper.imageDTOToImage(imageDTO);
        image.setIdimagen(imageDTO.getIdimagen());

        return mapper.imageToImageDTO(imagePersistence.updateImage(image));

    }

    @Override
    public void deleteImage(Integer id) {

        imagePersistence.deleteImage(id);
    }

}
