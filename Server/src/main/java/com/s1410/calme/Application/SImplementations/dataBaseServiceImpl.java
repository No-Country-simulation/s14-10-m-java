package com.s1410.calme.Application.SImplementations;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAppointment;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssistent;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Entities.Assisted;
import com.s1410.calme.Domain.Services.*;
import com.s1410.calme.Domain.Utils.Specialty;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class dataBaseServiceImpl implements DataBaseService {

    private final AssistentService assistentService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final AssistedService assistedService;

    @Override
    public String insertAssistant() {
        List<RequestCreateAssistent> requestAssistents = getInsertAssistents();
        
        requestAssistents.forEach(
                requestCreateAssistent -> {
                    try {
                        assistentService.createAssistent(requestCreateAssistent);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    
                });
        return requestAssistents.toString();
    }

    @Override
    public String insertDoctor() throws Exception {
        List<RequestCreateDoctor> requestCreateDoctors = getInsertDoctors();
        
        requestCreateDoctors.forEach(
                requestCreateDoctor -> {
                    try {
                        doctorService.createDoctor(requestCreateDoctor);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    
                }
        );
        return requestCreateDoctors.toString();
    }
    @Override
    public String insertAssisted() {
        List<RequestCreateAssisted> requestCreateAssisteds = getInsertAssisted();
        
        requestCreateAssisteds.forEach(
                requestCreateAssisted -> {
                    assistedService.createAssisted(requestCreateAssisted);
                    
                }
        );
        return requestCreateAssisteds.toString();
    }

    @Override
    public String insertAppointment() {

        List<RequestCreateAppointment> requestCreateAppointments = getInsertAppointment();
        
        requestCreateAppointments.forEach(
                requestCreateAppointment -> {
                    appointmentService.createAppointment(requestCreateAppointment);
                }
        );
        return requestCreateAppointments.toString();
    }

    @Override
    public ResponseEntity<?> insertAll() throws Exception {

        insertAssistant();
        insertAssisted();
        insertDoctor();
        insertAppointment();

        return ResponseEntity.ok("data ok");
    }

    /* DATA */

    /* 20 assistants */
    List<RequestCreateAssistent> getInsertAssistents(){

        List<RequestCreateAssistent> assistents = new ArrayList<>();

        assistents.add(new RequestCreateAssistent("juanortegaj22@gmail.com", "password1", "Juan", "Carlos", "Ortega", "123456789", 543517707973L, LocalDate.of(1990, 1, 1)));
        assistents.add(new RequestCreateAssistent("marialopezr@gmail.com", "password2", "Maria", "Isabel", "Lopez", "234567890", 543513849396L, LocalDate.of(1990, 1, 2)));
        assistents.add(new RequestCreateAssistent("pedrogarciaa@gmail.com", "password3", "Pedro", "Pablo", "Garcia", "345678901", 12345678903L, LocalDate.of(1990, 1, 3)));
        assistents.add(new RequestCreateAssistent("luciamartinb@gmail.com", "password4", "Lucia", "Ana", "Martin", "456789012", 12345678904L, LocalDate.of(1990, 1, 4)));
        assistents.add(new RequestCreateAssistent("carlosperezc@gmail.com", "password5", "Carlos", "Alberto", "Perez", "567890123", 12345678905L, LocalDate.of(1990, 1, 5)));
        assistents.add(new RequestCreateAssistent("sofiagonzalezd@gmail.com", "password6", "Sofia", "Valentina", "Gonzalez", "678901234", 12345678906L, LocalDate.of(1990, 1, 6)));
        assistents.add(new RequestCreateAssistent("andreafernandeze@gmail.com", "password7", "Andrea", "Fernanda", "Fernandez", "789012345", 12345678907L, LocalDate.of(1990, 1, 7)));
        assistents.add(new RequestCreateAssistent("davidrodriguezf@gmail.com", "password8", "David", "Diego", "Rodriguez", "890123456", 12345678908L, LocalDate.of(1990, 1, 8)));
        assistents.add(new RequestCreateAssistent("mariagomezh@gmail.com", "password9", "Maria", "Jose", "Gomez", "901234567", 12345678909L, LocalDate.of(1990, 1, 9)));
        assistents.add(new RequestCreateAssistent("luisroblese@gmail.com", "password10", "Luis", "Manuel", "Robles", "012345678", 12345678910L, LocalDate.of(1990, 1, 10)));
        assistents.add(new RequestCreateAssistent("carmencastillof@gmail.com", "password11", "Carmen", "Laura", "Castillo", "123456780", 12345678911L, LocalDate.of(1990, 1, 11)));
        assistents.add(new RequestCreateAssistent("javierhernandezg@gmail.com", "password12", "Javier", "Raul", "Hernandez", "234567891", 12345678912L, LocalDate.of(1990, 1, 12)));
        assistents.add(new RequestCreateAssistent("paulagonzalezh@gmail.com", "password13", "Paula", "Elena", "Gonzalez", "345678912", 12345678913L, LocalDate.of(1990, 1, 13)));
        assistents.add(new RequestCreateAssistent("danielramosj@gmail.com", "password14", "Daniel", "Santiago", "Ramos", "456789123", 12345678914L, LocalDate.of(1990, 1, 14)));
        assistents.add(new RequestCreateAssistent("mariasanchezk@gmail.com", "password15", "Maria", "Antonia", "Sanchez", "567891234", 12345678915L, LocalDate.of(1990, 1, 15)));
        assistents.add(new RequestCreateAssistent("julioalonsoi@gmail.com", "password16", "Julio", "Enrique", "Alonso", "678912345", 12345678916L, LocalDate.of(1990, 1, 16)));
        assistents.add(new RequestCreateAssistent("anagarridoj@gmail.com", "password17", "Ana", "Maria", "Garrido", "789123456", 12345678917L, LocalDate.of(1990, 1, 17)));
        assistents.add(new RequestCreateAssistent("albertolunak@gmail.com", "password18", "Alberto", "Javier", "Luna", "891234567", 12345678918L, LocalDate.of(1990, 1, 18)));
        assistents.add(new RequestCreateAssistent("susanaquirozl@gmail.com", "password19", "Susana", "Marina", "Quiroz", "912345678", 12345678919L, LocalDate.of(1990, 1, 19)));
        assistents.add(new RequestCreateAssistent("jorgesantiagom@gmail.com", "password20", "Jorge", "Fernando", "Santiago", "123456789", 12345678920L, LocalDate.of(1990, 1, 20)));

        return assistents;
    }
    /* 29 doctors*/
    List<RequestCreateDoctor> getInsertDoctors(){

        List<RequestCreateDoctor> doctor = new ArrayList<>();
        doctor.add(new RequestCreateDoctor("doctorjimenez@gmail.com", "password123", "Juan", "Primero", "Jimenez", "123456789", Specialty.NEUROLOGY, LocalDate.of(1985, 5, 15), 1234567890L, true, true, true, 12345, 987654321L, "Calle Principal 123"));
        doctor.add(new RequestCreateDoctor("doctorlopez@gmail.com", "password456", "Maria", null, "Lopez", "234567890", Specialty.PEDIATRICS, LocalDate.of(1980, 3, 10), 2345678901L, true, true, true, 23456, 876543210L, "Avenida Central 456"));
        doctor.add(new RequestCreateDoctor("doctorgonzalez@gmail.com", "password789", "Pedro", null, "Gonzalez", "345678901", Specialty.CARDIOLOGY, LocalDate.of(1975, 8, 20), 3456789012L, true, true, true, 34567, 765432109L, "Calle Mayor 789"));
        doctor.add(new RequestCreateDoctor("doctormartin@gmail.com", "passwordabc", "Lucia", null, "Martin", "456789012", Specialty.TRAUMATOLOGY, LocalDate.of(1990, 6, 5), 4567890123L, true, true, true, 45678, 654321098L, "Paseo del Sol 890"));
        doctor.add(new RequestCreateDoctor("doctorgomez@gmail.com", "passworddef", "Carlos", null, "Gomez", "567890123", Specialty.DERMATOLOGY, LocalDate.of(1988, 9, 12), 5678901234L, true, true, true, 56789, 543210987L, "Avenida del Bosque 456"));
        doctor.add(new RequestCreateDoctor("doctorhernandez@gmail.com", "passwordghi", "Sofia", null, "Hernandez", "678901234", Specialty.GERIATRICS, LocalDate.of(1972, 2, 25), 6789012345L, true, true, true, 67890, 432109876L, "Calle del Rio 789"));
        doctor.add(new RequestCreateDoctor("doctorfernandez@gmail.com", "passwordjkl", "Andrea", null, "Fernandez", "789012345", Specialty.HEMATOLOGY, LocalDate.of(1977, 11, 30), 7890123456L, false, true, false, 78901, 321098765L, "Avenida del Puerto 123"));
        doctor.add(new RequestCreateDoctor("doctortorres@gmail.com", "passwordmno", "David", null, "Torres", "890123456", Specialty.RADIOLOGY, LocalDate.of(1983, 4, 15), 8901234567L, true, false, true, 89012, 210987654L, "Callejon de la Luna 456"));
        doctor.add(new RequestCreateDoctor("doctorrodriguez@gmail.com", "passwordpqr", "Maria", null, "Rodriguez", "901234567", Specialty.PSYCHIATRY, LocalDate.of(1979, 7, 20), 9012345678L, false, false, true, 90123, 109876543L, "Calle del Pinar 789"));
        doctor.add(new RequestCreateDoctor("doctorgarcia@gmail.com", "passwordstu", "Luis", null, "Garcia", "012345678", Specialty.NEUROLOGY, LocalDate.of(1986, 10, 8), 9012345679L, true, true, false, 12345, 9876543210L, "Avenida de las Flores 890"));
        doctor.add(new RequestCreateDoctor("doctormoreno@gmail.com", "passwordvwx", "Carmen", null, "Moreno", "123456780", Specialty.CARDIOLOGY, LocalDate.of(1981, 12, 25), 1234567801L, false, true, true, 23456, 8765432109L, "Calle Mayor 456"));
        doctor.add(new RequestCreateDoctor("doctorruiz@gmail.com", "passwordyz1", "Javier", null, "Ruiz", "234567891", Specialty.PEDIATRICS, LocalDate.of(1989, 2, 14), 2345678912L, true, false, true, 34567, 7654321098L, "Paseo del Sol 789"));
        doctor.add(new RequestCreateDoctor("doctorperez@gmail.com", "password234", "Paula", null, "Perez", "345678912", Specialty.DERMATOLOGY, LocalDate.of(1984, 3, 5), 3456789123L, false, true, false, 45678, 6543210987L, "Avenida del Bosque 890"));
        doctor.add(new RequestCreateDoctor("doctorlopezm@gmail.com", "password890", "Maria", null, "Lopez", "567891234", Specialty.HEMATOLOGY, LocalDate.of(1982, 8, 10), 5678912345L, false, true, false, 67890, 4321098765L, "Avenida del Puerto 789"));
        doctor.add(new RequestCreateDoctor("doctortorresj@gmail.com", "passwordabc1", "Julio", null, "Torres", "678912345", Specialty.RADIOLOGY, LocalDate.of(1987, 11, 23), 6789123456L, true, false, true, 78901, 3210987654L, "Callejon de la Luna 123"));
        doctor.add(new RequestCreateDoctor("doctormartine@gmail.com", "passworddef2", "Ana", null, "Martinez", "789123456", Specialty.PSYCHIATRY, LocalDate.of(1978, 6, 15), 7891234567L, false, true, false, 89012, 2109876543L, "Calle del Pinar 456"));
        doctor.add(new RequestCreateDoctor("doctorgonzalezo@gmail.com", "passwordghi3", "Alberto", null, "Gonzalez", "891234567", Specialty.NEUROLOGY, LocalDate.of(1980, 9, 8), 8912345678L, true, false, true, 90123, 1098765432L, "Avenida de las Flores 123"));
        doctor.add(new RequestCreateDoctor("doctormoralese@gmail.com", "passwordjkl4", "Susana", null, "Morales", "912345678", Specialty.CARDIOLOGY, LocalDate.of(1983, 2, 28), 9123456789L, false, true, true,668, 1987654321L, "Calle Mayor 789"));
        doctor.add(new RequestCreateDoctor("doctorperezm@gmail.com", "password123", "Mario", null, "Perez", "123456789", Specialty.NEUROLOGY, LocalDate.of(1980, 5, 15), 1234567890L, true, false, true, 12345, 987654321L, "Calle Principal 123"));
        doctor.add(new RequestCreateDoctor("doctorrodriguezs@gmail.com", "password456", "Sara", null, "Rodriguez", "234567890", Specialty.PEDIATRICS, LocalDate.of(1975, 3, 10), 2345678901L, true, true, false, 23456, 876543210L, "Avenida Central 456"));
        doctor.add(new RequestCreateDoctor("doctorgomezg@gmail.com", "password789", "Gabriel", null, "Gomez", "345678901", Specialty.CARDIOLOGY, LocalDate.of(1970, 8, 20), 3456789012L, false, true, true, 34567, 765432109L, "Calle Mayor 789"));
        doctor.add(new RequestCreateDoctor("doctormartinezm@gmail.com", "passwordabc", "Laura", null, "Martinez", "456789012", Specialty.TRAUMATOLOGY, LocalDate.of(1990, 6, 5), 4567890123L, true, false, false, 45678, 654321098L, "Paseo del Sol 890"));
        doctor.add(new RequestCreateDoctor("doctorgarciah@gmail.com", "passworddef", "Hector", null, "Garcia", "567890123", Specialty.DERMATOLOGY, LocalDate.of(1988, 9, 12), 5678901234L, false, true, false, 56789, 543210987L, "Avenida del Bosque 456"));
        doctor.add(new RequestCreateDoctor("doctorlopeza@gmail.com", "passwordghi", "Ana", null, "Lopez", "678901234", Specialty.GERIATRICS, LocalDate.of(1972, 2, 25), 6789012345L, true, false, true, 67890, 432109876L, "Calle del Rio 789"));
        doctor.add(new RequestCreateDoctor("doctortorresr@gmail.com", "passwordjkl", "Ricardo", null, "Torres", "789012345", Specialty.HEMATOLOGY, LocalDate.of(1977, 11, 30), 7890123456L, false, true, false, 78901, 321098765L, "Avenida del Puerto 123"));
        doctor.add(new RequestCreateDoctor("doctorgomezb@gmail.com", "passwordmno", "Belen", null, "Gomez", "890123456", Specialty.RADIOLOGY, LocalDate.of(1983, 4, 15), 8901234567L, true, false, true, 89012, 210987654L, "Callejon de la Luna 456"));
        doctor.add(new RequestCreateDoctor("doctormartinezc@gmail.com", "passwordpqr", "Miguel", null, "Martinez", "901234567", Specialty.PSYCHIATRY, LocalDate.of(1979, 7, 20), 9012345678L, false, false, true, 90123, 109876543L, "Calle del Pinar 789"));
        doctor.add(new RequestCreateDoctor("doctorgomezr@gmail.com", "passwordstu", "Elena", null, "Gomez", "012345678", Specialty.NEUROLOGY, LocalDate.of(1986, 10, 8), 9012345679L, true, true, false, 12345, 9876543210L, "Avenida de las Flores 890"));
        doctor.add(new RequestCreateDoctor("doctormorenor@gmail.com", "passwordvwx", "Andres", null, "Moreno", "123456780", Specialty.CARDIOLOGY, LocalDate.of(1981, 12, 25), 1234567801L, false, true, true, 23456, 8765432109L, "Calle Mayor 456"));
        return doctor;
    }
    /* 40 asistidos */
    List<RequestCreateAssisted> getInsertAssisted(){
        List<RequestCreateAssisted> requestCreateAssisteds = new ArrayList<>();

        requestCreateAssisteds.add(new RequestCreateAssisted("123456789", "Ana", null, "Gonzalez", LocalDate.of(1980, 5, 15), 1L, "NUCLEAR_FAMILY"));
        requestCreateAssisteds.add(new RequestCreateAssisted("234567890", "Carlos", null, "Martinez", LocalDate.of(1975, 3, 10), 2L, "EXTENDED_FAMILY"));
        requestCreateAssisteds.add(new RequestCreateAssisted("345678901", "Laura", null, "Rodriguez", LocalDate.of(1970, 8, 20), 3L, "PROFESSIONAL"));
        requestCreateAssisteds.add(new RequestCreateAssisted("456789012", "Juan", null, "Lopez", LocalDate.of(1990, 6, 5), 4L, "NUCLEAR_FAMILY"));
        requestCreateAssisteds.add(new RequestCreateAssisted("567890123", "Maria", null, "Perez", LocalDate.of(1988, 9, 12), 5L, "EXTENDED_FAMILY"));
        requestCreateAssisteds.add(new RequestCreateAssisted("678901234", "David", null, "Garcia", LocalDate.of(1983, 4, 8), 6L, "PROFESSIONAL"));
        requestCreateAssisteds.add(new RequestCreateAssisted("789012345", "Sofia", null, "Sanchez", LocalDate.of(1977, 10, 25), 7L, "NUCLEAR_FAMILY"));
        requestCreateAssisteds.add(new RequestCreateAssisted("890123456", "Elena", null, "Fernandez", LocalDate.of(1985, 2, 17), 8L, "EXTENDED_FAMILY"));
        requestCreateAssisteds.add(new RequestCreateAssisted("901234567", "Javier", null, "Ruiz", LocalDate.of(1982, 7, 30), 9L, "PROFESSIONAL"));
        requestCreateAssisteds.add(new RequestCreateAssisted("012345678", "Laura", null, "Hernandez", LocalDate.of(1979, 9, 22), 10L, "NUCLEAR_FAMILY"));
        requestCreateAssisteds.add(new RequestCreateAssisted("123456780", "Manuel", null, "Diaz", LocalDate.of(1987, 11, 14), 11L, "EXTENDED_FAMILY"));
        requestCreateAssisteds.add(new RequestCreateAssisted("234567891", "Natalia", null, "Martinez", LocalDate.of(1973, 6, 7), 12L, "PROFESSIONAL"));
        requestCreateAssisteds.add(new RequestCreateAssisted("345678912", "Pablo", null, "Lopez", LocalDate.of(1989, 3, 18), 13L, "NUCLEAR_FAMILY"));
        requestCreateAssisteds.add(new RequestCreateAssisted("456789123", "Carmen", null, "Sanchez", LocalDate.of(1976, 8, 29), 14L, "EXTENDED_FAMILY"));
        requestCreateAssisteds.add(new RequestCreateAssisted("567891234", "Daniel", null, "Garcia", LocalDate.of(1984, 5, 3), 15L, "PROFESSIONAL"));
        requestCreateAssisteds.add(new RequestCreateAssisted("678912345", "Lucia", null, "Fernandez", LocalDate.of(1981, 4, 12), 16L, "NUCLEAR_FAMILY"));
        requestCreateAssisteds.add(new RequestCreateAssisted("789123456", "Marta", null, "Perez", LocalDate.of(1978, 7, 27), 17L, "EXTENDED_FAMILY"));
        requestCreateAssisteds.add(new RequestCreateAssisted("891234567", "Alejandro", null, "Ruiz", LocalDate.of(1986, 1, 9), 18L, "PROFESSIONAL"));
        requestCreateAssisteds.add(new RequestCreateAssisted("912345678", "Sara", null, "Gomez", LocalDate.of(1980, 12, 5), 19L, "NUCLEAR_FAMILY"));
        requestCreateAssisteds.add(new RequestCreateAssisted("023456789", "Diego", null, "Martinez", LocalDate.of(1974, 9, 16), 20L, "EXTENDED_FAMILY"));


        return requestCreateAssisteds;
    }
    /* 40 appointments */
    List<RequestCreateAppointment> getInsertAppointment(){
        LocalDateTime FutureDate = LocalDateTime.now().plusDays(3);
        int Year = FutureDate.getYear();
        int Month = FutureDate.getMonthValue();
        int Day = FutureDate.getDayOfMonth();
        int Day2 = FutureDate.plusDays(1).getDayOfMonth();
        int demoDay = FutureDate.plusDays(2).getDayOfMonth();
        int Hour = FutureDate.getHour();
        int Minute = FutureDate.getMinute();

        if (FutureDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            FutureDate = FutureDate.plusDays(1);
        }

        List<RequestCreateAppointment> appointments = new ArrayList<>();

        appointments.add(new RequestCreateAppointment(1L, null, 1L, LocalDateTime.of(Year, Month, demoDay, 10, 0), "Consulta de gastroenterología para evaluación de problemas digestivos"));
        appointments.add(new RequestCreateAppointment(1L, null, 2L, LocalDateTime.of(Year, Month, demoDay, 11, 30), "Control de presión arterial y seguimiento de dieta baja en sodio"));
        appointments.add(new RequestCreateAppointment(1L, null, 4L, LocalDateTime.of(Year, Month, Day, 12, 0), "Sesión de terapia de grupo para manejo de la ansiedad"));
        appointments.add(new RequestCreateAppointment(1L, null, 5L, LocalDateTime.of(Year, Month, Day, 13, 0), "Consulta de psiquiatría para ajuste de medicación"));
        appointments.add(new RequestCreateAppointment(1L, null, 6L, LocalDateTime.of(Year, Month, Day, 14, 30), "Consulta de oftalmología para evaluación de cataratas"));
        appointments.add(new RequestCreateAppointment(2L, null, 17L, LocalDateTime.of(Year, Month, Day, 15, 30), "Consulta de traumatología para tratamiento de lesión de tobillo"));
        appointments.add(new RequestCreateAppointment(2L, null, 18L, LocalDateTime.of(Year, Month, Day, 16, 0), "Consulta de alergología para evaluación de reacciones alérgicas"));
        appointments.add(new RequestCreateAppointment(2L, null, 19L, LocalDateTime.of(Year, Month, Day, 17, 0), "Consulta de neurología para evaluación de migrañas"));
        appointments.add(new RequestCreateAppointment(2L, null, 10L, LocalDateTime.of(Year, Month, Day, 18, 30), "Consulta de neumología para evaluación de síntomas respiratorios"));
        appointments.add(new RequestCreateAppointment(2L, 20L, null, LocalDateTime.of(Year, Month, Day, 19, 0), "Consulta de reumatología para manejo de artritis"));
        appointments.add(new RequestCreateAppointment(3L, 1L, null, LocalDateTime.of(Year, Month, Day, 9, 0), "Consulta de oncología para seguimiento de tratamiento contra el cáncer"));
        appointments.add(new RequestCreateAppointment(3L, 2L, null, LocalDateTime.of(Year, Month, Day, 10, 0), "Consulta de endocrinología para evaluación de tiroides"));
        appointments.add(new RequestCreateAppointment(3L, 3L, null, LocalDateTime.of(Year, Month, Day, 11, 30), "Consulta de geriatría para evaluación de salud en la vejez"));
        appointments.add(new RequestCreateAppointment(3L, 4L, null, LocalDateTime.of(Year, Month, Day, 12, 0), "Consulta de nutrición para planificación de dieta saludable"));
        appointments.add(new RequestCreateAppointment(4L, 5L, null, LocalDateTime.of(Year, Month, Day, 13, 0), "Consulta de cirugía plástica para evaluación de cirugía estética"));
        appointments.add(new RequestCreateAppointment(4L, 6L, null, LocalDateTime.of(Year, Month, Day, 14, 30), "Consulta de medicina deportiva para evaluación de lesión deportiva"));
        appointments.add(new RequestCreateAppointment(4L, 7L, null, LocalDateTime.of(Year, Month, Day,15, 0), "Consulta de medicina interna para evaluación de síntomas generales"));
        appointments.add(new RequestCreateAppointment(4L, 8L, null, LocalDateTime.of(Year, Month, Day, 16, 0), "Consulta de hematología para evaluación de trastornos de la coagulación"));
        appointments.add(new RequestCreateAppointment(4L, 9L, null, LocalDateTime.of(Year, Month, Day, 17, 0), "Consulta de odontología para tratamiento de caries"));
        appointments.add(new RequestCreateAppointment(5L, 10L, null, LocalDateTime.of(Year, Month, Day2, 18, 30), "Consulta de psicoterapia para manejo de trastorno de ansiedad"));
        appointments.add(new RequestCreateAppointment(5L, 11L, null, LocalDateTime.of(Year, Month, Day2, 19, 30), "Consulta de reumatología para manejo de artritis"));
        appointments.add(new RequestCreateAppointment(5L, null, 12L, LocalDateTime.of(Year, Month, Day2, 9, 0), "Consulta de oncología para seguimiento de tratamiento contra el cáncer"));
        appointments.add(new RequestCreateAppointment(5L, null, 13L, LocalDateTime.of(Year, Month, Day2, 10, 0), "Consulta de endocrinología para evaluación de tiroides"));
        appointments.add(new RequestCreateAppointment(5L, null, 14L, LocalDateTime.of(Year, Month, Day2, 12, 30), "Consulta de geriatría para evaluación de salud en la vejez"));
        appointments.add(new RequestCreateAppointment(1L, null, 15L, LocalDateTime.of(Year, Month, Day2, 13, 30), "Consulta de nutrición para planificación de dieta saludable"));
        appointments.add(new RequestCreateAppointment(1L, null, 16L, LocalDateTime.of(Year, Month, Day2, 14, 0), "Consulta de cirugía plástica para evaluación de cirugía estética"));
        appointments.add(new RequestCreateAppointment(1L, null, 17L, LocalDateTime.of(Year, Month, Day2, 15, 30), "Consulta de medicina deportiva para evaluación de lesión deportiva"));
        appointments.add(new RequestCreateAppointment(1L, null, 18L, LocalDateTime.of(Year, Month, Day2, 16, 0), "Consulta de medicina interna para evaluación de síntomas generales"));
        appointments.add(new RequestCreateAppointment(1L, null, 5L, LocalDateTime.of(Year, Month, Day2, 17, 0), "Consulta de hematología para evaluación de trastornos de la coagulación"));
        appointments.add(new RequestCreateAppointment(2L, null, 20L, LocalDateTime.of(Year, Month, Day2, 18, 0), "Consulta de odontología para tratamiento de caries"));
        appointments.add(new RequestCreateAppointment(2L, null, 1L, LocalDateTime.of(Year, Month, Day2, 19, 30), "Consulta de psicoterapia para manejo de trastorno de ansiedad"));
        appointments.add(new RequestCreateAppointment(2L, null, 2L, LocalDateTime.of(Year, Month, Day2, 9, 30), "Consulta de urología para evaluación de problemas de próstata"));
        appointments.add(new RequestCreateAppointment(2L, null, 3L, LocalDateTime.of(Year, Month, Day2, 10, 0), "Consulta de dermatología para tratamiento de acné"));
        appointments.add(new RequestCreateAppointment(2L, null, 4L, LocalDateTime.of(Year, Month, Day2, 11, 0), "Consulta de otorrinolaringología para tratamiento de sinusitis"));
        appointments.add(new RequestCreateAppointment(3L, 4L, null, LocalDateTime.of(Year, Month, Day2, 12, 30), "Consulta de ginecología para examen de rutina"));
        appointments.add(new RequestCreateAppointment(3L, 5L, null, LocalDateTime.of(Year, Month, Day2, 13, 30), "Consulta de cardiología para evaluación de ritmo cardíaco"));
        appointments.add(new RequestCreateAppointment(3L, 6L, null, LocalDateTime.of(Year, Month, Day2, 14, 0), "Consulta de pediatría para seguimiento de desarrollo infantil"));
        appointments.add(new RequestCreateAppointment(3L, 7L, null, LocalDateTime.of(Year, Month, Day2, 15, 0), "Consulta de fisioterapia para rehabilitación de lesión"));
        appointments.add(new RequestCreateAppointment(3L, 8L, null, LocalDateTime.of(Year, Month, Day2, 16, 30), "Consulta de nutrición para seguimiento de dieta"));
        appointments.add(new RequestCreateAppointment(4L, 9L, null, LocalDateTime.of(Year, Month, Day2, 17, 0), "Consulta de psiquiatría para manejo de trastorno de ansiedad"));
        return appointments;
    }


    /*
    @PostConstruct
    void levantar(){
        insertAll();
    }*/


}
