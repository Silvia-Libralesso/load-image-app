package com.nttdata.loadimageapp.service;

import com.nttdata.loadimageapp.controllers.ImageDTO;
import com.nttdata.loadimageapp.domain.model.Image;
import com.nttdata.loadimageapp.domain.repository.ImagePersistence;
import com.nttdata.loadimageapp.domain.service.ImageService;
import com.nttdata.loadimageapp.service.mapper.ImageImageDTOMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    private ImagePersistence imagePersistence;
    private ImageImageDTOMapper mapper;

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

        Image image = mapper.imageDTOToImage(imageDTO);

        return mapper.imageToImageDTO(imagePersistence.createImage(image));

    }

    @Override
    public ImageDTO updateImage(ImageDTO imageDTO) {

        Image image = mapper.imageDTOToImage(imageDTO);
        image.setIdImage(imageDTO.getIdImage());

        return mapper.imageToImageDTO(imagePersistence.updateImage(image));

    }

    @Override
    public void deleteImage(Integer id) {

        imagePersistence.deleteImage(id);
    }

}
