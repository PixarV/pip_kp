package com.itmo.contollers;

import com.itmo.services.EngineService;
import com.pip.entities.Engine;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Controller
@AllArgsConstructor
@RequestMapping("/engines")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class EngineController {

    EngineService engineService;

    @ResponseBody
    @GetMapping("/getAll")
    List<Engine> findAllEngines() {
        return engineService.findAllEngines();
    }

    @ResponseBody
    @PostMapping("/add")
    Engine addEngine(@RequestBody Engine engine) {
        engineService.addEngine(engine);
        return engine;
    }

    @ResponseBody
    @GetMapping("/get/{engineId}")
    Engine getEngine(@PathVariable int engineId) {
        return engineService.getEngineById(engineId);
    }

    @ResponseBody
    @PutMapping("/update")
    Engine updateEngine(@RequestBody Engine engine) {
        return engineService.updateEngine(engine);
    }

    @DeleteMapping("/delete")
    void deleteEngine(@RequestBody Engine engine) {
        engineService.deleteEngine(engine);
    }

    @DeleteMapping("/delete/{engineId}")
    void deleteEngineById(@PathVariable int engineId) {
        engineService.deleteEngineById(engineId);
    }
}
