package com.s1410.calme.Application.SImplementations;
import com.s1410.calme.Domain.Dtos.response.ResponseAssistent;
import com.s1410.calme.Domain.Entities.Appointment;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Entities.RelationAA;
import com.s1410.calme.Domain.Mapper.AssistentMapper;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



@SpringBootTest
public class JAssistantServiceImplTest {
/*
    @Autowired
    AssistentServiceImpl assistentServiceImpl;
    @MockBean
    AssistentRepository assistentRepository;
    @Autowired
    AssistentMapper assistentMapper;

    Long id; String email; String password; String DNI;
    String firstName; String secondName; String lastName;
    LocalDate dateOfBirth; boolean active;
    ArrayList<RelationAA> relationsAA ;
    ArrayList<Appointment> appointmentList;
    Assistent assistent = new Assistent();
    ResponseAssistent responseAssistent;


    @BeforeEach
    void setUp() {
        System.out.println("before(A)");
        id = 1l;
        email = "pedropascal123@gmail.com";
        password = "Pedro123!";
        firstName = "Pedro";
        secondName = "Roberto";
        lastName = "Pascal";
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

        responseAssistent = new ResponseAssistent(id,email,firstName, secondName,
                lastName, DNI,dateOfBirth,relationsAA);
    }

    @Test
    void environment() {
        System.out.println("environment up");
    }
/*
    @Nested
    @DisplayName("Test readAssistent")
    class readMethods {

        @Test
        void readByIdSucessfuly() {

            Mockito.when(assistentRepository.findById(id)).thenReturn(Optional.ofNullable(assistent));
            ResponseAssistent responseAssistentExpected = assistentServiceImpl.readAssistent(id);
            assertEquals(responseAssistent,responseAssistentExpected);
        }

        @Test
        void readByIdIfNotExistId() {
            id = 4803L;
            Mockito.when(assistentRepository.findById(id)).thenReturn(Optional.empty());
            //System.out.println(assistentServiceImpl.readAssistent(2L));

            assertThrows(EntityNotFoundException.class,
                    () -> {
                        assistentServiceImpl.readAssistent(id);
                    });
        }

        @Test
        public void readAllByActive() {
            boolean active = true;
            Pageable paging = Pageable.unpaged();

            List<Assistent> assistents = new ArrayList<>();
            assistents.add(assistent);
            Page<Assistent> assistentsPage = new PageImpl<>(assistents);

            List<ResponseAssistent> responseAssistents = new ArrayList<>();
            responseAssistents.add(responseAssistent);
            Page<ResponseAssistent> responseAssistentsPage = new PageImpl<>(responseAssistents);

            Mockito.when(assistentRepository.findAllByActive(active, paging))
                    .thenReturn(assistentsPage);

            Page<ResponseAssistent> result = assistentServiceImpl.readAllAsistents(active, paging);

            assertEquals(result , responseAssistentsPage);
        }
    }

    @Nested
    @DisplayName("Test deleteAssistent")
    class deleteMethods{

        @Test
        @DisplayName("Verify if change status")
        void toogleDelete() {
            Boolean status = assistent.getActive();
            Mockito.when(assistentRepository.save(assistent)).thenReturn(assistent);
            Mockito.when(assistentRepository.findById(id)).thenReturn(Optional.ofNullable(assistent));

            Boolean statusExpected = assistentServiceImpl.toogleDeleteAssistent(id);

            assertEquals(statusExpected,!status);
        }

    }*/


}
