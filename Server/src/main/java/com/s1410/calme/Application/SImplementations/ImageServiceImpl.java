package com.s1410.calme.Application.SImplementations;

import com.cloudinary.Cloudinary;
import com.s1410.calme.Domain.Services.ImageService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Resource
    private Cloudinary cloudinary;

    @Override
    public String uploadImageCloudinary(MultipartFile image) {

        try {
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", "profileImages");

            Map uploadedFile = cloudinary.uploader().upload(image.getBytes(), options);

            String publicId = uploadedFile.get("public_id").toString();

            return cloudinary.url().secure(true).generate(publicId);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
