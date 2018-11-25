package com.pip.controllers;

import com.pip.entities.Engine;
import com.pip.enums.TypeFuel;
import com.pip.services.EngineService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Controller
@AllArgsConstructor
@RequestMapping("/engines")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class EngineContoller {

    EngineService engineService;

    @GetMapping
    @ResponseBody
    List<Engine> findAllFirms() {
        return engineService.findAllEngines();
    }

    @ResponseBody
    @GetMapping("/add")
    String addEngine() {
        Engine engine = Engine.builder()
                .weight(4242)
                .title("probe")
                .fuel(TypeFuel.DIZEL)
                .power(1005000)
                .build();
        engineService.addEngine(engine);
        return "hey";
    }
}
