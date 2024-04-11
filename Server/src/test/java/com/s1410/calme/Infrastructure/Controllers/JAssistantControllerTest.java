package com.s1410.calme.Infrastructure.Controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.s1410.calme.Domain.Dtos.response.ResponseAssistent;
import com.s1410.calme.Domain.Entities.Appointment;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Entities.RelationAA;
import com.s1410.calme.Domain.Services.AssistentService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WithMockUser
public class JAssistantControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AssistentService assistentService;

    @Autowired
    AssistentController assistentController;

    @Autowired
    private JacksonTester<ResponseAssistent> responseAssistentJacksonTester;



    @Autowired
    private JacksonTester<Page<ResponseAssistent>> responseAssistentJacksonTesterPage;

    /*
    Assistent assistent = new Assistent();
    Long id; String email; String password; String DNI;
    LocalDate dateOfBirth; boolean active;
    ArrayList<RelationAA> relationsAA ;
    ArrayList<Appointment> appointmentList;

    ResponseAssistent responseAssistent;

    ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        System.out.println("beforeEach");
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

        responseAssistent = new ResponseAssistent(id,email,DNI,dateOfBirth,relationsAA);

        objectMapper.registerModule(new JavaTimeModule());// add dateTime module mapper for jackson.
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @Nested
    @DisplayName("Reading Tests")
    public class readMethods{

        @Test
        void readByIdSucesfully() throws Exception {
            Mockito.when(
                    assistentService.readAssistent(id))
                    .thenReturn(responseAssistent);
            String responseResultJson = objectMapper.writeValueAsString(responseAssistent);
            var expectedJson = responseAssistentJacksonTester.write(
                    responseAssistent).getJson();
            mockMvc.perform(
                    get("/assistent/id/" + id)
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().json(expectedJson));

        }

        @Test
        void readAllByActive() throws Exception {
            Pageable paging = Pageable.unpaged();
            active = false;
            List<ResponseAssistent> responseAssistents = new ArrayList<>();
            Page<ResponseAssistent> responseAssistentsPage = new PageImpl<>(responseAssistents);

            Mockito.when(assistentService.readAllAsistents(active,paging)).
                    thenReturn(responseAssistentsPage);

            mockMvc.perform(get("/assistent/all/" + active))
                    .andExpect(status().isOk());

            var response = assistentController.findAllAssistents(active,paging);
            assertEquals(responseAssistentsPage , response.getBody());
        }
    }
    @Nested
    @DisplayName("Deleting Tests")
    public class deleteMethods{
        @Test
        void toggleDelete() throws Exception {

            Boolean expected = !active;

            Mockito.when(assistentService.toogleDeleteAssistent(id)).thenReturn(expected);

            var response = assistentController.deleteAssistent(id);

            mockMvc.perform(delete("/assistent/id/" + id))
                    .andExpect(status().isOk());

            assertEquals(response.getBody(),expected);
        }
    } */
}
