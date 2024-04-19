package com.s1410.calme.Application.SImplementations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.s1410.calme.Domain.Services.ImageService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Resource
    private Cloudinary cloudinary;

    @Override
    public String uploadProfileImageCloudinary(MultipartFile image, String name) {

        try {
            //Set image file upload options
            Map options = ObjectUtils.asMap(
                    "unique_filename", false,
                    "overwrite", true,
                    "public_id", name,
                    "folder", "profileImages"
            );

            //Upload image to Cloudinary
            Map uploadedFile = cloudinary.uploader().upload(image.getBytes(), options);

            String publicId = uploadedFile.get("public_id").toString();

            return cloudinary.url().secure(true).generate(publicId);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
