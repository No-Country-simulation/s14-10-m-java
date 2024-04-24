package com.s1410.calme.Infrastructure.Exceptions.custom;

public class AppointmentAvailabilityException extends RuntimeException{
    public AppointmentAvailabilityException(String message) {
        super(message);
    }
}
