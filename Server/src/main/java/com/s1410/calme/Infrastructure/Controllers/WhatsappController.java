package com.s1410.calme.Infrastructure.Controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.s1410.calme.Domain.Dtos.whatsapp.MessageBodyDTO;
import com.s1410.calme.Domain.Dtos.whatsapp.ResponseWhatsapp;
import com.s1410.calme.Domain.Services.ApiWhatsappService;
import com.s1410.calme.Domain.Services.AssistentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/reminder")
    boolean sendMessage() throws JsonProcessingException {
        return apiWhatsappService.sendMessage();
    }

    @PostMapping("/test")
    ResponseWhatsapp sendMessageTest(@RequestBody MessageBodyDTO payload) throws JsonProcessingException {
        System.out.println("entra a la funcion");

        return apiWhatsappService.sendMessageTest(payload);
    }

}