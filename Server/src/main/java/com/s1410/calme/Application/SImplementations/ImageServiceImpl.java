package com.s1410.calme.Application.SImplementations;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.s1410.calme.Domain.Entities.Assisted;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Entities.Doctor;
import com.s1410.calme.Domain.Repositories.AssistedRepository;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
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

    public final DoctorRepository doctorRepository;
    public final AssistentRepository assistentRepository;
    public final AssistedRepository assistedRepository;

    @Override
    public String saveDoctorProfilePicture(MultipartFile image, Long doctorId) {

        //Verify that the doctor exists in the database.
        Doctor doctor = this.doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + doctorId + " not Found."));

        //Upload image in cloudinary. Return image link.
        String imageUrl = uploadImageToCloudinary(image, "profiles_images/doctor", "image_" + doctor.getId().toString());

        //Set new imageUrl to doctor
        doctor.setImageUrl(imageUrl);
        this.doctorRepository.save(doctor);

        return imageUrl;
    }

    @Override
    public String saveAssistantProfilePicture(MultipartFile image, Long assistantId) {

        //Verify that the assistant exists in the database.
        Assistent assistant = this.assistentRepository.findById(assistantId)
                .orElseThrow(() -> new EntityNotFoundException("Assistant with ID " + assistantId + " not Found."));

        //Upload image in cloudinary. Return image link.
        String imageUrl = uploadImageToCloudinary(image, "profiles_images/assistant", "image_" + assistant.getId().toString());

        //Set new imageUrl to assistant
        assistant.setImageUrl(imageUrl);
        this.assistentRepository.save(assistant);

        return imageUrl;
    }

    @Override
    public String saveAssistedProfilePicture(MultipartFile image, Long assistedId) {

        //Verify that the assisted exists in the database.
        Assisted assisted = this.assistedRepository.findById(assistedId)
                .orElseThrow(() -> new EntityNotFoundException("Assisted with ID " + assistedId + " not Found."));

        //Upload image in cloudinary. Return image link.
        String imageUrl = uploadImageToCloudinary(image, "profiles_images/assisted", "image_" + assisted.getId().toString());

        //Set new imageUrl to assisted
        assisted.setImageUrl(imageUrl);
        this.assistedRepository.save(assisted);

        return imageUrl;
    }

    private String uploadImageToCloudinary(MultipartFile image, String folderPath, String nameFile) {

        try {
            // Set image upload options
            Map options = ObjectUtils.asMap(
                    "unique_filename", true,
                    "overwrite", true,
                    "public_id", nameFile,
                    "folder", folderPath,
                    "resource_type", "image",
                    "transformation", new Transformation<>()
                            .aspectRatio("1.0").width(450).crop("fill")
            );

            //Upload image to cloudinary with the above options.
            Map uploadedFile = cloudinary.uploader().upload(image.getBytes(), options);

            //Return image link
            return uploadedFile.get("secure_url").toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    };
}
