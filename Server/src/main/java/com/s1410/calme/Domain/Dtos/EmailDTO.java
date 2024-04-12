package com.s1410.calme.Domain.Dtos;

import lombok.Getter;
import lombok.Setter;


public class EmailDTO {

    private String Addressee;
    private String Subject;
    private String message;

    public String getAddressee() {
        return Addressee;
    }

    public void setAddressee(String addressee) {
        Addressee = addressee;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
