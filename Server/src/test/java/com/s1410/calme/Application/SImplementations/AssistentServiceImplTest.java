package com.s1410.calme.Application.SImplementations;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssistent;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssistent;
import com.s1410.calme.Domain.Entities.Appointment;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Entities.RelationAA;
import com.s1410.calme.Domain.Mapper.AssistentMapper;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AssistentServiceImplTest {

    @MockBean
    private AssistentRepository assistentRepository;
    @Autowired
    private AssistentMapper assistentMapper;

    AssistentServiceImpl assistentServiceImpl;
    Long id; String email; String password; String DNI;
    LocalDate dateOfBirth; boolean active;
    ArrayList<RelationAA> relationsAA ;
    ArrayList<Appointment> appointmentList;

    Assistent assistent = new Assistent();

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
        assistent.setId(id);
        assistent.setDNI(DNI);
        assistent.setDateOfBirth(dateOfBirth);
        //assistent.setAppointmentList(appointmentList);
        assistent.setActive(true);
        assistent.setEmail(email);
        assistent.setPassword(password);
        assistent.setRelationsAA(relationsAA);
    }

    @BeforeEach
    public void setUpUserService() {
        assistentServiceImpl = new AssistentServiceImpl(assistentMapper, assistentRepository);
    }

    @Nested
    @DisplayName("Tests on create methods, both positive and negative cases")
    class CreateTests{
        @Test
        void shouldCreateAssistent(){
            RequestCreateAssistent requestCreateAssistent = new RequestCreateAssistent(
                    email, password, DNI, dateOfBirth);

            Assistent assistent = new Assistent(email, password, relationsAA);
            assistent.setId(id);
            assistent.setDNI("12345678");
            assistent.setDateOfBirth(LocalDate.of(1979, Month.JUNE, 20));
            //assistent.setAppointmentList(appointmentList);
            assistent.setActive(true);

            when(assistentRepository.save(any())).thenReturn(assistent);
            var result = assistentServiceImpl.createAssistent(requestCreateAssistent);

            verify(assistentRepository, times(1)).save(any());
            assertEquals(assistent.getEmail(), result.email());
            assertEquals(assistent.getDNI(), result.DNI());
            assertEquals(assistent.getDateOfBirth(), result.dateOfBirth());
        }

        @Test
        void shouldNotCreateAssistentWhenNull(){
            /*
            RequestCreateAssistent requestCreateAssistent = null;

            Exception thrown = Assertions.assertThrows(UserNotNullException.class, () -> {
                assistentServiceImpl.createAssistent(requestCreateAssistent);
            });

            Assertions.assertEquals(UserNotNullException.
                    USER_CANT_BE_NULL, thrown.getMessage());*/
        }
    }

    @Nested
    @DisplayName("Tests on update methods, both positive and negative cases")
    class UpdateTests {

        @Test
        void shouldUpdateAssistent() {
            RequestEditAssistent requestEditAssistent = new RequestEditAssistent(id, email,
                    password, DNI, dateOfBirth);

            Assistent assistent = new Assistent(email, password, relationsAA);
            assistent.setId(id);
            assistent.setDNI("12345678");
            assistent.setDateOfBirth(LocalDate.of(1979, Month.JUNE, 20));
            //assistent.setAppointmentList(appointmentList);
            assistent.setActive(true);

            when(assistentRepository.findById(any())).thenReturn(Optional.of(assistent));
            when(assistentRepository.save(any())).thenReturn(assistent);
            var result = assistentServiceImpl.updateAssistent(requestEditAssistent);

            verify(assistentRepository, times(1)).save(assistent);
            assertEquals(assistent.getId(), requestEditAssistent.id());
            assertEquals(assistent.getEmail(), result.email());
            assertEquals(assistent.getPassword(), requestEditAssistent.password());
            assertEquals(assistent.getDNI(), result.DNI());
            assertEquals(assistent.getDateOfBirth(), result.dateOfBirth());
        }

        @Test
        void shouldNotUpdateAttributesToNull() {
            RequestEditAssistent requestEditAssistent = new RequestEditAssistent(id,
                    null, null,
                    null, null);

            Assistent assistent = new Assistent(email, password, relationsAA);
            assistent.setId(id);
            assistent.setDNI("12345678");
            assistent.setDateOfBirth(LocalDate.of(1979, Month.JUNE, 20));
            //assistent.setAppointmentList(appointmentList);
            assistent.setActive(true);

            when(assistentRepository.findById(any())).thenReturn(Optional.of(assistent));
            when(assistentRepository.save(any())).thenReturn(assistent);
            var result = assistentServiceImpl.updateAssistent(requestEditAssistent);

            verify(assistentRepository, times(1)).save(assistent);
            assertEquals(assistent.getId(), requestEditAssistent.id());
            assertNotNull(result.email());
            assertNotNull(result.DNI());
            assertNotNull(result.dateOfBirth());
        }

        @Test
        void shouldNotUpdateAssistentWithNotFoundId() {/*
            RequestEditAssistent requestEditAssistent = new RequestEditAssistent(id, email,
                    password, DNI, dateOfBirth);

            Assistent assistent = new Assistent(email, password, relationsAA);
            assistent.setId(132456789L);
            assistent.setDNI("12345678");
            assistent.setDateOfBirth(LocalDate.of(1979, Month.JUNE, 20));
            //assistent.setAppointmentList(appointmentList);
            assistent.setActive(true);

            when(assistentRepository.findById(any())).thenReturn(Optional.empty());
            Exception thrown = Assertions.assertThrows(UserNotFoundException.class, () -> {
                assistentServiceImpl.updateAssistent(requestEditAssistent);
            });

            Assertions.assertEquals(UserNotFoundException.
                    USER_NOT_EXISTS_BY_ID_TEXT + requestEditAssistent.id(), thrown.getMessage());
        */}

        @Test
        void shouldNotUpdateAssistentIfIsNotActive(){
            RequestEditAssistent requestEditAssistent = new RequestEditAssistent(id, email,
                    password, DNI, dateOfBirth);

            Assistent assistent = new Assistent(email, password, relationsAA);
            assistent.setId(132456789L);
            assistent.setDNI("12345678");
            assistent.setDateOfBirth(LocalDate.of(1979, Month.JUNE, 20));
            //assistent.setAppointmentList(appointmentList);
            assistent.setActive(false);

            when(assistentRepository.findById(any())).thenReturn(Optional.of(assistent));
                assistentServiceImpl.updateAssistent(requestEditAssistent);

            verify(assistentRepository, times(0)).save(assistent);
        }
    }

/*    @Nested
    @DisplayName("")
    class getAssistentById {
        @Test
        void getAssistentByIdSucesfully() {
            // id , assistent , repository
            ResponseAssistent responseAssistent = new ResponseAssistent(
                    id, email,DNI, dateOfBirth , relationsAA , appointmentList);
            when(assistentRepository.findById(id)).thenReturn(Optional.ofNullable(assistent));
            var result = assistentServiceImpl.readAssistent(responseAssistent.id());

            assertEquals(result , assistent);

        }
    }*/
    }

