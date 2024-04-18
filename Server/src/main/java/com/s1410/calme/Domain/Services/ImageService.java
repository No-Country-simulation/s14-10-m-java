package com.s1410.calme.Domain.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

public interface ImageService {

     String uploadImageCloudinary(MultipartFile image);

}

