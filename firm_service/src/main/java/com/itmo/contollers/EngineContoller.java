package com.itmo.contollers;

import com.pip.entities.Engine;
import com.pip.enums.TypeFuel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.itmo.services.EngineService;

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
    List<Engine> findAllEngines() {
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
