package com.project.mrsisa.service;

import com.project.mrsisa.domain.Image;
import com.project.mrsisa.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;


    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public List<Image> findAll(){
        return imageRepository.findAll();
    }


    public Image findOneById(Long id) {
        return imageRepository.findOneById(id);
    }

    public List<Image> findAllByAdventureId(Long id){
        return imageRepository.findAllByOfferId(id);
    }

    public List<Image> findAllByCottageId(Long id){
        return imageRepository.findAllByOfferId(id);
    }
}
