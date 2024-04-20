package com.s1410.calme.Domain.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.s1410.calme.Domain.Dtos.whatsapp.ResponseWhatsapp;

import java.util.List;

public interface ApiWhatsappService {

    List<ResponseWhatsapp> sendAllReminders() throws JsonProcessingException;

}
