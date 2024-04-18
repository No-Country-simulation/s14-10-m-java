package com.s1410.calme.Domain.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.s1410.calme.Domain.Dtos.whatsapp.MessageBodyDTO;
import com.s1410.calme.Domain.Dtos.whatsapp.ResponseWhatsapp;

import java.util.List;

public interface ApiWhatsappService {

    public Boolean sendMessage() throws JsonProcessingException;
    public ResponseWhatsapp sendMessageTest(String message) throws JsonProcessingException;
    List<ResponseWhatsapp> createMessageTest () throws JsonProcessingException;

}
