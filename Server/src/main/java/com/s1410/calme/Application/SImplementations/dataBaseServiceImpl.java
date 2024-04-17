package com.s1410.calme.Application.SImplementations;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAppointment;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssistent;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Entities.Assisted;
import com.s1410.calme.Domain.Services.*;
import com.s1410.calme.Domain.Utils.Specialty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        System.out.println("entro al service insertAssistant()");
        requestAssistents.forEach(
                requestCreateAssistent -> {
                    assistentService.createAssistent(requestCreateAssistent);
                    System.out.println(requestCreateAssistent.firstName());
                });
        return requestAssistents.toString();
    }

    @Override
    public String insertDoctor() {
        List<RequestCreateDoctor> requestCreateDoctors = getInsertDoctors();
        System.out.println("entro al service insertDoctor()");
        requestCreateDoctors.forEach(
                requestCreateDoctor -> {
                    doctorService.createDoctor(requestCreateDoctor);
                    System.out.println(requestCreateDoctor.firstName());
                }
        );
        return requestCreateDoctors.toString();
    }
    @Override
    public String insertAssisted() {
        List<RequestCreateAssisted> requestCreateAssisteds = getInsertAssisted();
        System.out.println("entro al service insertAssisted()");
        requestCreateAssisteds.forEach(
                requestCreateAssisted -> {
                    assistedService.createAssisted(requestCreateAssisted);
                    System.out.println(requestCreateAssisted.firstName());
                }
        );
        return requestCreateAssisteds.toString();
    }

    @Override
    public String insertAppointment() {

        List<RequestCreateAppointment> requestCreateAppointments = getInsertAppointment();
        System.out.println("entro al service insertAppointment()");
        requestCreateAppointments.forEach(
                requestCreateAppointment -> {
                    appointmentService.createAppointment(requestCreateAppointment);
                }
        );
        return requestCreateAppointments.toString();
    }

    @Override
    public String insertAll() {
        insertAssistant();
        insertAssisted();
        insertDoctor();
        insertAppointment();

        return "todo ok";
    }

    /* DATA */

    /* 20 assistants */
    List<RequestCreateAssistent> getInsertAssistents(){

        List<RequestCreateAssistent> assistents = new ArrayList<>();

        assistents.add(new RequestCreateAssistent("juanortegaj22@gmail.com", "password1", "Juan", "Carlos", "Ortega", "123456789", 12345678901L, LocalDate.of(1990, 1, 1)));
        assistents.add(new RequestCreateAssistent("marialopezr@gmail.com", "password2", "Maria", "Isabel", "Lopez", "234567890", 12345678902L, LocalDate.of(1990, 1, 2)));
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
        doctor.add(new RequestCreateDoctor("doctorjimenez@gmail.com", "password123", "Juan", null, "Jimenez", "123456789", Specialty.NEUROLOGY, LocalDate.of(1985, 5, 15), 1234567890L, true, false, true, 12345, 987654321L, "Calle Principal 123"));
        doctor.add(new RequestCreateDoctor("doctorlopez@gmail.com", "password456", "Maria", null, "Lopez", "234567890", Specialty.PEDIATRICS, LocalDate.of(1980, 3, 10), 2345678901L, true, true, false, 23456, 876543210L, "Avenida Central 456"));
        doctor.add(new RequestCreateDoctor("doctorgonzalez@gmail.com", "password789", "Pedro", null, "Gonzalez", "345678901", Specialty.CARDIOLOGY, LocalDate.of(1975, 8, 20), 3456789012L, false, true, true, 34567, 765432109L, "Calle Mayor 789"));
        doctor.add(new RequestCreateDoctor("doctormartin@gmail.com", "passwordabc", "Lucia", null, "Martin", "456789012", Specialty.TRAUMATOLOGY, LocalDate.of(1990, 6, 5), 4567890123L, true, false, false, 45678, 654321098L, "Paseo del Sol 890"));
        doctor.add(new RequestCreateDoctor("doctorgomez@gmail.com", "passworddef", "Carlos", null, "Gomez", "567890123", Specialty.DERMATOLOGY, LocalDate.of(1988, 9, 12), 5678901234L, false, true, false, 56789, 543210987L, "Avenida del Bosque 456"));
        doctor.add(new RequestCreateDoctor("doctorhernandez@gmail.com", "passwordghi", "Sofia", null, "Hernandez", "678901234", Specialty.GERIATRICS, LocalDate.of(1972, 2, 25), 6789012345L, true, false, true, 67890, 432109876L, "Calle del Rio 789"));
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
    /* 30 appointments */
    List<RequestCreateAppointment> getInsertAppointment(){
        List<RequestCreateAppointment> appointments = new ArrayList<>();
/*        appointments.add(new RequestCreateAppointment(1L, 15L, 8L, LocalDateTime.now().minusDays(3), "Consulta de rutina para chequeo médico"));
        appointments.add(new RequestCreateAppointment(2L, 5L, 17L, LocalDateTime.now().minusDays(5), "Seguimiento de tratamiento para control de la presión arterial"));
        appointments.add(new RequestCreateAppointment(3L, 14L, 3L, LocalDateTime.now().minusDays(2), "Consulta por dolor abdominal persistente"));
        appointments.add(new RequestCreateAppointment(2L, 2L, 18L, LocalDateTime.now().minusDays(6), "Vacunación contra la gripe"));
        appointments.add(new RequestCreateAppointment(5L, 9L, 6L, LocalDateTime.now().minusDays(4), "Examen de sangre para análisis de glucosa"));
        appointments.add(new RequestCreateAppointment(6L, 11L, 16L, LocalDateTime.now().minusDays(1), "Seguimiento de rehabilitación por lesión en el hombro"));
        appointments.add(new RequestCreateAppointment(7L, 10L, 12L, LocalDateTime.now().minusDays(3), "Consulta pediátrica para vacunación infantil"));
        appointments.add(new RequestCreateAppointment(8L, 7L, 19L, LocalDateTime.now().minusDays(5), "Control de presión arterial y ajuste de medicación"));
        appointments.add(new RequestCreateAppointment(9L, 4L, 13L, LocalDateTime.now().minusDays(2), "Consulta ginecológica de rutina"));
        appointments.add(new RequestCreateAppointment(10L, 1L, 20L, LocalDateTime.now().minusDays(6), "Consulta odontológica para limpieza y revisión"));
        appointments.add(new RequestCreateAppointment(11L, 8L, 15L, LocalDateTime.now().minusDays(2), "Consulta de seguimiento para control de la diabetes"));
        appointments.add(new RequestCreateAppointment(12L, 17L, 5L, LocalDateTime.now().minusDays(4), "Examen de vista y prescripción de lentes"));
        appointments.add(new RequestCreateAppointment(13L, 3L, 14L, LocalDateTime.now().minusDays(1), "Consulta por síntomas de gripe"));
        appointments.add(new RequestCreateAppointment(14L, 18L, 2L, LocalDateTime.now().minusDays(3), "Consulta de dermatología para tratamiento de acné"));
        appointments.add(new RequestCreateAppointment(15L, 6L, 9L, LocalDateTime.now().minusDays(5), "Consulta de psicología para manejo del estrés"));
        appointments.add(new RequestCreateAppointment(16L, 16L, 11L, LocalDateTime.now().minusDays(2), "Sesión de fisioterapia para recuperación de lesión de rodilla"));
        appointments.add(new RequestCreateAppointment(17L, 12L, 10L, LocalDateTime.now().minusDays(4), "Control de peso y nutrición"));
        appointments.add(new RequestCreateAppointment(18L, 19L, 7L, LocalDateTime.now().minusDays(1), "Consulta por síntomas de alergia"));
        appointments.add(new RequestCreateAppointment(19L, 13L, 4L, LocalDateTime.now().minusDays(3), "Examen ginecológico de rutina"));
        appointments.add(new RequestCreateAppointment(20L, 20L, 1L, LocalDateTime.now().minusDays(5), "Extracción de muela de juicio"));
        appointments.add(new RequestCreateAppointment(2L, 1L, 2L, LocalDateTime.now().minusDays(3), "Consulta de seguimiento postoperatorio"));
        appointments.add(new RequestCreateAppointment(2L, 2L, 3L, LocalDateTime.now().minusDays(4), "Control de glucosa en ayunas"));
        appointments.add(new RequestCreateAppointment(3L, 3L, 4L, LocalDateTime.now().minusDays(5), "Consulta de cardiología para evaluación de ritmo cardíaco"));*/

        appointments.add(new RequestCreateAppointment(1L, 11L, 12L, LocalDateTime.of(2024, 4, 22, 10, 0), "Consulta de gastroenterología para evaluación de problemas digestivos"));
        appointments.add(new RequestCreateAppointment(2L, 12L, 13L, LocalDateTime.of(2024, 4, 21, 11, 30), "Control de presión arterial y seguimiento de dieta baja en sodio"));
        appointments.add(new RequestCreateAppointment(3L, 13L, 14L, LocalDateTime.of(2024, 4, 20, 13, 15), "Sesión de terapia de grupo para manejo de la ansiedad"));
        appointments.add(new RequestCreateAppointment(4L, 14L, 15L, LocalDateTime.of(2024, 4, 19, 15, 45), "Consulta de psiquiatría para ajuste de medicación"));
        appointments.add(new RequestCreateAppointment(5L, 15L, 16L, LocalDateTime.of(2024, 4, 18, 8, 30), "Consulta de oftalmología para evaluación de cataratas"));
        appointments.add(new RequestCreateAppointment(6L, 16L, 17L, LocalDateTime.of(2024, 4, 17, 10, 15), "Consulta de traumatología para tratamiento de lesión de tobillo"));
        appointments.add(new RequestCreateAppointment(7L, 17L, 18L, LocalDateTime.of(2024, 4, 16, 12, 45), "Consulta de alergología para evaluación de reacciones alérgicas"));
        appointments.add(new RequestCreateAppointment(8L, 18L, 19L, LocalDateTime.of(2024, 4, 15, 14, 0), "Consulta de neurología para evaluación de migrañas"));
        appointments.add(new RequestCreateAppointment(9L, 19L, 20L, LocalDateTime.of(2024, 4, 14, 16, 30), "Consulta de neumología para evaluación de síntomas respiratorios"));
        appointments.add(new RequestCreateAppointment(10L, 20L, 1L, LocalDateTime.of(2024, 4, 13, 9, 45), "Consulta de reumatología para manejo de artritis"));
        appointments.add(new RequestCreateAppointment(1L, 1L, 2L, LocalDateTime.of(2024, 4, 12, 11, 0), "Consulta de oncología para seguimiento de tratamiento contra el cáncer"));
        appointments.add(new RequestCreateAppointment(2L, 2L, 3L, LocalDateTime.of(2024, 4, 11, 13, 15), "Consulta de endocrinología para evaluación de tiroides"));
        appointments.add(new RequestCreateAppointment(3L, 3L, 4L, LocalDateTime.of(2024, 4, 10, 15, 30), "Consulta de geriatría para evaluación de salud en la vejez"));
        appointments.add(new RequestCreateAppointment(4L, 4L, 5L, LocalDateTime.of(2024, 4, 9, 17, 45), "Consulta de nutrición para planificación de dieta saludable"));
        appointments.add(new RequestCreateAppointment(5L, 5L, 6L, LocalDateTime.of(2024, 4, 8, 10, 0), "Consulta de cirugía plástica para evaluación de cirugía estética"));
        appointments.add(new RequestCreateAppointment(6L, 6L, 7L, LocalDateTime.of(2024, 4, 7, 14, 30), "Consulta de medicina deportiva para evaluación de lesión deportiva"));
        appointments.add(new RequestCreateAppointment(7L, 7L, 8L, LocalDateTime.of(2024, 4, 6, 16, 45), "Consulta de medicina interna para evaluación de síntomas generales"));
        appointments.add(new RequestCreateAppointment(8L, 8L, 9L, LocalDateTime.of(2024, 4, 5, 8, 0), "Consulta de hematología para evaluación de trastornos de la coagulación"));
        appointments.add(new RequestCreateAppointment(9L, 9L, 10L, LocalDateTime.of(2024, 4, 4, 10, 15), "Consulta de odontología para tratamiento de caries"));
        appointments.add(new RequestCreateAppointment(10L, 10L, 1L, LocalDateTime.of(2024, 4, 3, 12, 30), "Consulta de psicoterapia para manejo de trastorno de ansiedad"));
        appointments.add(new RequestCreateAppointment(10L, 10L, 11L, LocalDateTime.of(2024, 3, 13, 9, 45), "Consulta de reumatología para manejo de artritis"));
        appointments.add(new RequestCreateAppointment(1L, 1L, 12L, LocalDateTime.of(2024, 2, 12, 11, 0), "Consulta de oncología para seguimiento de tratamiento contra el cáncer"));
        appointments.add(new RequestCreateAppointment(2L, 2L, 13L, LocalDateTime.of(2024, 3, 11, 13, 15), "Consulta de endocrinología para evaluación de tiroides"));
        appointments.add(new RequestCreateAppointment(3L, 3L, 14L, LocalDateTime.of(2024, 3, 10, 15, 30), "Consulta de geriatría para evaluación de salud en la vejez"));
        appointments.add(new RequestCreateAppointment(4L, 4L, 15L, LocalDateTime.of(2024, 2, 9, 17, 45), "Consulta de nutrición para planificación de dieta saludable"));
        appointments.add(new RequestCreateAppointment(5L, 5L, 16L, LocalDateTime.of(2024, 3, 8, 11, 0), "Consulta de cirugía plástica para evaluación de cirugía estética"));
        appointments.add(new RequestCreateAppointment(6L, 6L, 17L, LocalDateTime.of(2024, 2, 7, 15, 30), "Consulta de medicina deportiva para evaluación de lesión deportiva"));
        appointments.add(new RequestCreateAppointment(7L, 7L, 18L, LocalDateTime.of(2024, 3, 6, 18, 45), "Consulta de medicina interna para evaluación de síntomas generales"));
        appointments.add(new RequestCreateAppointment(7L, 8L, 5L, LocalDateTime.of(2024, 4, 5, 7, 0), "Consulta de hematología para evaluación de trastornos de la coagulación"));
        appointments.add(new RequestCreateAppointment(9L, 9L, 20L, LocalDateTime.of(2024, 3, 4, 10, 15), "Consulta de odontología para tratamiento de caries"));
        appointments.add(new RequestCreateAppointment(10L, 10L, 1L, LocalDateTime.of(2024, 2, 3, 12, 30), "Consulta de psicoterapia para manejo de trastorno de ansiedad"));
        appointments.add(new RequestCreateAppointment(1L, 1L, 2L, LocalDateTime.of(2024, 4, 2, 14, 45), "Consulta de urología para evaluación de problemas de próstata"));
        appointments.add(new RequestCreateAppointment(2L, 2L, 3L, LocalDateTime.of(2024, 4, 1, 17, 0), "Consulta de dermatología para tratamiento de acné"));
        appointments.add(new RequestCreateAppointment(3L, 3L, 4L, LocalDateTime.of(2024, 3, 31, 9, 15), "Consulta de otorrinolaringología para tratamiento de sinusitis"));
        appointments.add(new RequestCreateAppointment(4L, 4L, 5L, LocalDateTime.of(2024, 2, 29, 11, 30), "Consulta de ginecología para examen de rutina"));
        appointments.add(new RequestCreateAppointment(5L, 5L, 6L, LocalDateTime.of(2024, 3, 29, 13, 45), "Consulta de cardiología para evaluación de ritmo cardíaco"));
        appointments.add(new RequestCreateAppointment(6L, 6L, 7L, LocalDateTime.of(2024, 2, 28, 16, 0), "Consulta de pediatría para seguimiento de desarrollo infantil"));
        appointments.add(new RequestCreateAppointment(7L, 7L, 8L, LocalDateTime.of(2024, 1, 27, 8, 15), "Consulta de fisioterapia para rehabilitación de lesión"));
        appointments.add(new RequestCreateAppointment(8L, 8L, 9L, LocalDateTime.of(2024, 4, 26, 10, 30), "Consulta de nutrición para seguimiento de dieta"));
        appointments.add(new RequestCreateAppointment(9L, 9L, 10L, LocalDateTime.of(2024, 4, 25, 12, 45), "Consulta de psiquiatría para manejo de trastorno de ansiedad"));
        return appointments;
    }



}
