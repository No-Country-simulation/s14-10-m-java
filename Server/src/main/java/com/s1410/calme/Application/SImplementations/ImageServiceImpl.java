package com.s1410.calme.Application.SImplementations;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.s1410.calme.Domain.Entities.Assisted;
import com.s1410.calme.Domain.Repositories.AssistedRepository;
import com.s1410.calme.Domain.Services.ImageService;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
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

    public final AssistedRepository assistedRepository;

    @Override
    public String saveAssistedProfilePicture(MultipartFile image, Long assistedId) {

        Assisted assisted = this.assistedRepository.findById(assistedId)
                .orElseThrow(() -> new EntityNotFoundException("Assisted with ID " + assistedId + " not Found."));

        String imageUrl = uploadImageToCloudinary(image, "profiles_images/assisted", "image_" + assisted.getId().toString());

        assisted.setImageUrl(imageUrl);
        this.assistedRepository.save(assisted);

        return imageUrl;
    }

    private String uploadImageToCloudinary(MultipartFile image, String folderPath, String nameFile) {

        try {
            // Set image options
            Map options = ObjectUtils.asMap(
                    "unique_filename", true,
                    "overwrite", true,
                    "public_id", nameFile,
                    "folder", folderPath,
                    "resource_type", "image",
                    "transformation", new Transformation<>()
                            .aspectRatio("1.0").width(450).crop("fill")
            );

            //Upload image to Cloudinary
            Map uploadedFile = cloudinary.uploader().upload(image.getBytes(), options);

            return uploadedFile.get("secure_url").toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    };
}
