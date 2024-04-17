package com.s1410.calme.Infrastructure.Controllers;


import com.s1410.calme.Domain.Services.DataBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
@RequiredArgsConstructor
public class DataBaseController {

    public final DataBaseService dataBaseService;
    @GetMapping("/assistent")
    String insertAssistant(){
        return dataBaseService.insertAssistant();
    }

    @GetMapping("/doctor")
    String insertDoctor(){
        return dataBaseService.insertDoctor();
    }
    @GetMapping("/assisted")
    String insertAssisted(){
        return dataBaseService.insertAssisted();
    }
    @GetMapping("/appointment")
    String insertAppointment(){
        System.out.println("entro al controlador");
        return dataBaseService.insertAppointment();
    }

    @GetMapping("/all")
    String insertAll(){
        System.out.println("entro al controlador");
        return dataBaseService.insertAll();
    }

}
