package com.s1410.calme.Domain.Services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

     String saveAssistedProfilePicture(MultipartFile image, Long assistedId);

}

