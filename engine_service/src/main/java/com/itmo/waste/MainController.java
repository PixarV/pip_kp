package com.itmo.waste;

import com.pip.entities.Engine;
import com.pip.services.EngineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {

    EngineService engineService;

    @GetMapping("/getAll")
    @ResponseBody
    List<Engine> getAllFirms() {
        System.out.println("hey");
        return engineService.findAllEngines();
    }
}
