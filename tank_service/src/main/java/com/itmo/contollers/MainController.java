package com.itmo.contollers;

import com.itmo.services.ChassisService;
import com.pip.entities.Chassis;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {
    ChassisService chassisService;

    @GetMapping("/getAll")
    @ResponseBody
    List<Chassis> getAllChassis() {
        System.out.println("hey");
        return chassisService.findAllChassiss();
    }
}
