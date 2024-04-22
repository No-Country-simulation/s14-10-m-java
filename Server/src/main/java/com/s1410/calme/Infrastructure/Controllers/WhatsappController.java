package com.s1410.calme.Infrastructure.Controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.s1410.calme.Domain.Dtos.whatsapp.ResponseWhatsapp;
import com.s1410.calme.Domain.Services.ApiWhatsappService;
import com.s1410.calme.Domain.Services.AssistentService;
import com.s1410.calme.Infrastructure.Exceptions.ApiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/whatsapp")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class WhatsappController {

    public final ApiWhatsappService apiWhatsappService;

    /* just accepted number 543517707973 (number by Juan Ortega)
    *{
    "number" : "543517707973",
    "message" : "recordatorio"
    * */

    @Operation(summary = "Send Appointment reminders", description = "Sends a WhatsApp reminder to appointments that are within the reminder timeframe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully sent reminders"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error sending reminders", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @PostMapping("/reminder")
    List<ResponseWhatsapp> sendMessage() throws JsonProcessingException {
        return apiWhatsappService.sendAllReminders();
    }





}