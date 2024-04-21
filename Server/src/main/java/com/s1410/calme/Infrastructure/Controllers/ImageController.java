package com.s1410.calme.Infrastructure.Controllers;

import com.s1410.calme.Domain.Services.ImageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public class ImageController {

    public final ImageService imageService;

    //Endpoint to update a user's profile image.
    //Send requestPath image as form-data (Postman) or FormData (Js/Ts)

    @PutMapping("/update/doctor/{doctorId}")
    public ResponseEntity<String> updateDoctorProfileImage(
            @PathVariable Long doctorId,
            @RequestPart("image") @NonNull MultipartFile image
    ) {
        return ResponseEntity.ok(this.imageService.saveDoctorProfilePicture(image, doctorId));
    }

    @PutMapping("/update/assistant/{assistantId}")
    public ResponseEntity<String> updateAssistantProfileImage(
            @PathVariable Long assistantId,
            @RequestPart("image") @NonNull MultipartFile image
    ) {
        return ResponseEntity.ok(this.imageService.saveAssistantProfilePicture(image, assistantId));
    }

    @PutMapping("/update/assisted/{assistedId}")
    public ResponseEntity<String> updateAssistedProfileImage(
            @PathVariable Long assistedId,
            @RequestPart("image") @NonNull MultipartFile image
    ) {
        return ResponseEntity.ok(this.imageService.saveAssistedProfilePicture(image, assistedId));
    }

}
