package com.s1410.calme.Infrastructure.Controllers;

import com.s1410.calme.Domain.Dtos.request.RequestAppointmentDate;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAppointment;
import com.s1410.calme.Domain.Dtos.request.RequestAppointmentBetweenDates;
import com.s1410.calme.Domain.Dtos.request.RequestEditAppointmentDate;
import com.s1410.calme.Domain.Dtos.response.ResponseAppointment;
import com.s1410.calme.Domain.Services.AppointmentService;
import com.s1410.calme.Infrastructure.Exceptions.ApiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/register")
    @Operation(summary = "Create an appointment", description = "Creates an appointment and stores it in the database. Must pass a series of checks in backend.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully created appointment"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "400", description = "Invalid Appointment Body", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error creating appointment", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    public ResponseEntity<ResponseAppointment> createAppointment(@RequestBody RequestCreateAppointment requestCreateAppointment){
        return appointmentService.createAppointment(requestCreateAppointment);
    }


    @Operation(summary = "Get all appointments", description = "Retrieves all of the appointments. Can be filtered by its active status.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved appointments"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving appointments", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/all")
    public ResponseEntity<List<ResponseAppointment>> getAllAppointments(@RequestParam Integer page, @RequestParam Boolean active){
        return appointmentService.getAllAppointments(page, active);
    }


    @Operation(summary = "Get an appointment by id", description = "Retrieves all data of an appointment by passing its id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved appointment"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No appointment found with passed id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving appointment", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseAppointment> getAppointmentsById(@PathVariable Long id){
        return appointmentService.getAppointmentById(id);
    }


    @Operation(summary = "Update appointment active status", description = "Changes an appointment status from True to False or from False to True. " +
            "Appointment is passed by its id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully created appointment"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No appointment found with passed id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error updating appointment", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseAppointment> changeAppointmentActiveValue(@PathVariable Long id){
        return appointmentService.changeAppointmentActiveValue(id);
    }



    @Operation(summary = "Get appointment between two dates", description = "Retrieves appointments that are scheduled between two dates. Can be filtered by its active status.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved appointments"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "400", description = "Invalid Date Body", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving appointments", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/betweenDates")
    public ResponseEntity<List<ResponseAppointment>> getAppointmentsBetweenDates(@RequestBody RequestAppointmentBetweenDates dates,
                                                                                 @RequestParam Integer page,
                                                                                 @RequestParam Boolean active){
        return appointmentService.getAppointmentsBetweenDates(dates, page, active);
    }


    @Operation(summary = "Update appointment date", description = "Updates an appointment scheduled date.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated appointment"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "400", description = "Invalid Appointment Body", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "No appointment found with the passed id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error updating appointment", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @PutMapping("/{id}/date")
    public ResponseEntity<ResponseAppointment> updateAppointmentDate(@RequestBody RequestEditAppointmentDate updatedDate,
                                                                     @PathVariable Long id){
        return appointmentService.updateAppointmentDate(updatedDate, id);
    }



    @Operation(summary = "Get appointments of a doctor", description = "Retrieves all appointments from a doctor. Can be filtered by its active status.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved appointments"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No doctor found with the passed id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving appointments", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/doctor/{id}")
    public  ResponseEntity<List<ResponseAppointment>> getDoctorAppointments(@RequestParam Boolean active, @PathVariable Long id){
        return appointmentService.getAppointmentByDoctorID(id, active);
    }


    @Operation(summary = "Get appointments of an assistent", description = "Retrieves all appointments from an assistent. Can be filtered by its active status.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved appointments"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No assistent found with the passed id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving appointments", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/assistent/{id}")
    public  ResponseEntity<List<ResponseAppointment>> getAssistentAppointments(@RequestParam Integer page, @RequestParam Boolean active, @PathVariable Long id){
        return appointmentService.getAppointmentByAssistentID(id, active, page);
    }



    @Operation(summary = "Get appointments of an assisted", description = "Retrieves all appointments from an assisted. Can be filtered by its active status.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved appointments"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No assisted found with the passed id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving appointments", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/assisted/{id}")
    public  ResponseEntity<List<ResponseAppointment>> getAssistedApponintments(@RequestParam Integer page, @RequestParam Boolean active, @PathVariable Long id){
        return appointmentService.getAppointmentByAssistedId(id, active, page);
    }


    @Operation(summary = "Get appointments of a date", description = "Retrieves all that are scheduled on a specific date. Can be filtered by its active status.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved appointments"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "400", description = "Invalid Date Body", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving appointments", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/date/{active}")
    public ResponseEntity<List<ResponseAppointment>> getAppointmentByDate(
            @RequestBody RequestAppointmentDate date,
            @PathVariable Boolean active){
        return appointmentService.getAppointmentsByDate(date, active);
    }
}
