package com.s1410.calme.Domain.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


public class AssistentTest {/*
    Long id; String email; String password; String DNI;
    LocalDate dateOfBirth; boolean active;
    ArrayList<RelationAA> relationsAA ;
    ArrayList<Appointment> appointmentList;

    @BeforeEach
    public void setUpAttributes(){
        id = 1l;
        email = "pedropascal123@gmail.com";
        password = "Pedro123!";
        DNI = "12345678";
        dateOfBirth = LocalDate.of(1980, Month.JULY, 23);
        active = true;
        relationsAA = new ArrayList<>();
        appointmentList = new ArrayList<>();
    }

    @Test
    void shouldCreateAssistentWithEmptyConstructor() {
        Assistent assistent = new Assistent();
        assertThat(assistent).isNotNull();
        assertThat(assistent.getActive()).isTrue();
    }

    @Test
    void shouldCreateAssistentWithFullConstructor() {
        Assistent assistent = new Assistent(email, password, relationsAA);
        assistent.setId(id); //Attributes from User can not be tested without setting them.
        assistent.setDNI(DNI);
        assistent.setDateOfBirth(dateOfBirth);
        //assistent.setAppointmentList(appointmentList);
        assistent.setActive(true);

        assertThat(assistent).isNotNull();
        assertThat(assistent.getId()).isEqualTo(id);
        assertThat(assistent.getEmail()).isEqualTo(email);
        assertThat(assistent.getPassword()).isEqualTo(password);
        assertThat(assistent.getDNI()).isEqualTo(DNI);
        assertThat(assistent.getDateOfBirth()).isEqualTo(dateOfBirth);
        assertThat(assistent.getActive()).isTrue();
        assertThat(assistent.getRelationsAA()).hasSize(0);
        //assertThat(assistent.getAppointmentList()).hasSize(0);
    }

    @Test
    void shouldSetAttributesToUser() {
        Assistent assistent = new Assistent();
        assistent.setId(id);
        assistent.setDNI(DNI);
        assistent.setDateOfBirth(dateOfBirth);
        //assistent.setAppointmentList(appointmentList);
        assistent.setActive(true);
        assistent.setEmail(email);
        assistent.setPassword(password);
        assistent.setRelationsAA(relationsAA);

        assertThat(assistent).isNotNull();
        assertThat(assistent.getId()).isEqualTo(id);
        assertThat(assistent.getEmail()).isEqualTo(email);
        assertThat(assistent.getPassword()).isEqualTo(password);
        assertThat(assistent.getDNI()).isEqualTo(DNI);
        assertThat(assistent.getDateOfBirth()).isEqualTo(dateOfBirth);
        assertThat(assistent.getActive()).isTrue();
        assertThat(assistent.getRelationsAA()).hasSize(0);
        //assertThat(assistent.getAppointmentList()).hasSize(0);
}*/

}
