package com.s1410.calme.Infrastructure.Controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s1410.calme.Application.SImplementations.AssistentServiceImpl;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssistent;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssistent;
import com.s1410.calme.Domain.Dtos.response.ResponseAssistent;
import com.s1410.calme.Domain.Entities.Appointment;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Entities.RelationAA;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Services.AssistentService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest //Test de SpringBoot porque Controller.
@AutoConfigureMockMvc //Configura el manejo de endpoints (post, put, get, delete, patch).
@WithMockUser //Habilita la autenticación (evita el error 403)
@AutoConfigureJsonTesters //Permite manejarse con DTO en lugar de requerir JSON.
public class AssistentControllerTest {

    @Autowired //Como el test está fuera del contexto de SpringBoot no pueden hacerse por constructor.
    private MockMvc mvc;
    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Autowired
    private JacksonTester<RequestCreateAssistent> requestCreateAssistentJacksonTester;
    @Autowired
    private JacksonTester<RequestEditAssistent> requestEditAssistentJacksonTester;
    @Autowired
    private JacksonTester<ResponseAssistent> responseAssistentJacksonTester;
    @MockBean //Suplanta el service y evita hacer la llamada a base de datos original.
    private AssistentServiceImpl assistentServiceImpl;

    Long id;
    String email;
    String password;
    String firstName;
    String secondName;
    String lastName;
    String DNI;
    LocalDate dateOfBirth;
    boolean active;
    ArrayList<RelationAA> relationsAA;
    ArrayList<Appointment> appointmentList;

    @BeforeEach
    public void setUpAttributes() {
        id = 1l;
        email = "pedropascal123@gmail.com";
        firstName = "Pedro";
        secondName = "Roberto";
        lastName = "Pascal";
        DNI = "12345678";
        dateOfBirth = LocalDate.of(1980, Month.JULY, 23);
        active = true;
        relationsAA = new ArrayList<>();
        //appointmentList = new ArrayList<>();
    }

    @BeforeEach
    public void setUpAssistentAttributes() {
        Assistent assistent = new Assistent();
        assistent.setId(id);
        assistent.setDNI(DNI);
        assistent.setDateOfBirth(dateOfBirth);
        //assistent.setAppointmentList(appointmentList);
        assistent.setActive(true);
        assistent.setEmail(email);
        assistent.setPassword(password);
        assistent.setRelationsAA(relationsAA);
    }
/*
    @Nested
    @DisplayName("Tests on create Assistent.")
    class CreateAssistentTests {

        @Test
        @DisplayName("Should return http201 when provided data is valid.")
        void createAssistentTest1() throws Exception {
            RequestCreateAssistent requestCreateAssistent =
                    new RequestCreateAssistent(email, password, firstName, secondName,
                            lastName,DNI, dateOfBirth);
            ResponseAssistent responseAssistent =
                    new ResponseAssistent(id, email, firstName, secondName,
                            lastName,DNI, dateOfBirth, relationsAA);
            when(assistentServiceImpl.createAssistent(any(RequestCreateAssistent.class)))
                    .thenReturn(responseAssistent);
            mvc.perform(post("/assistent/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestCreateAssistent)))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(responseAssistent.id()))
                    .andExpect(jsonPath("$.email").value(responseAssistent.email()))
                    .andExpect(jsonPath("$.DNI").value(responseAssistent.DNI()))
                    .andExpect(jsonPath("$.firstName").value(responseAssistent.firstName()))
                    .andExpect(jsonPath("$.secondName").value(responseAssistent.secondName()))
                    .andExpect(jsonPath("$.lastName").value(responseAssistent.lastName()))
                    .andExpect(jsonPath("$.dateOfBirth").value(responseAssistent.dateOfBirth().toString()))
                    .andExpect(jsonPath("$.relationsAA").value(responseAssistent.relationsAA()));
        }

/*
        @Test
        @DisplayName("Should return http400 when provided data NOT is valid.")
        void createAssistentTest2() throws Exception {
            RequestCreateAssistent requestCreateAssistent =
                    new RequestCreateAssistent(email, null, DNI, null);

            when(assistentServiceImpl.createAssistent(any(RequestCreateAssistent.class)))
                    .thenThrow(IllegalArgumentException.class);
            mvc.perform(post("/assistent/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestCreateAssistent)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Tests on update assistent, both positive and negative cases")
    class UpdateAssistentTest {

        @Test
        @DisplayName("Should return http200 when updating assistent by id")
        void updateAssistentTest1() throws Exception {
            RequestEditAssistent requestEditAssistent = new RequestEditAssistent(
                    id, email, password, DNI, dateOfBirth);

            ResponseAssistent responseAssistent = new ResponseAssistent(
                    id,email, DNI, dateOfBirth, relationsAA);

            when(assistentServiceImpl.updateAssistent(requestEditAssistent))
                    .thenReturn(responseAssistent);

            var response = mvc.perform(put("/assistent/update")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestEditAssistentJacksonTester.write(
                                    requestEditAssistent).getJson()))
                            .andReturn().getResponse();

            var expectedJson = responseAssistentJacksonTester.write(
                    responseAssistent).getJson();

            assertEquals(response.getContentAsString(), expectedJson);
            assertEquals(response.getStatus(), 200);
        }

        @Test
        @DisplayName("Should return http404 when NOT updating user by id")
        void updateAssistentTest2() throws Exception {
            RequestEditAssistent requestEditAssistent = new RequestEditAssistent(
                    id, email, password, DNI, dateOfBirth);

            when(assistentServiceImpl.updateAssistent(requestEditAssistent)).thenThrow(
                    EntityNotFoundException.class);

            var response = mvc.perform(put("/assistent/update")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestEditAssistentJacksonTester.write(
                                    requestEditAssistent).getJson()))
                            .andReturn().getResponse();

            assertEquals(404, response.getStatus());
        }
    }


*/
    }
