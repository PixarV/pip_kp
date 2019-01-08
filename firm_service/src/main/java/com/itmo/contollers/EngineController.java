package com.itmo.contollers;

import com.itmo.services.EngineService;
import com.pip.entities.Engine;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@AllArgsConstructor
@RequestMapping("/engines")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class EngineController {

    EngineService engineService;

    @GetMapping("/getAll")
    List<Engine> findAllEngines() {
        return engineService.findAllEngines();
    }

    @PostMapping("/add")
    Engine addEngine(@RequestBody Engine engine) {
        engineService.addEngine(engine);
        return engine;
    }

    @GetMapping("/get/{engineId}")
    Engine getEngine(@PathVariable int engineId) {
        return engineService.getEngineById(engineId);
    }

    @PutMapping("/update")
    void updateEngine(@RequestBody Engine engine) {
        engineService.updateEngine(engine);
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
