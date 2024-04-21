package com.s1410.calme.Domain.Services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

     String saveDoctorProfilePicture(MultipartFile image, Long doctorId);

     String saveAssistantProfilePicture(MultipartFile image, Long assistantId);

     String saveAssistedProfilePicture(MultipartFile image, Long assistedId);
}

