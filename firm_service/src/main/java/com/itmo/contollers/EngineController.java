package com.itmo.contollers;

import com.itmo.services.EngineService;
import com.pip.entities.Engine;
import com.pip.entities.Model;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
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

    @PostMapping("/add/{firmId}")
    Engine addEngine(@RequestBody Engine engine, @PathVariable int firmId) {
        engineService.addEngine(engine, firmId);
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

    @PutMapping("/removeModelEngine/{engineId}")
    void removeEngineFromMtoM(@PathVariable int engineId) {
        engineService.removeModelFromMtoM(engineId);
    }

    @GetMapping("/getModels/{engineId}")
    List<Model> getModels(@PathVariable int engineId) {
        return engineService.getModels(engineId);
    }

    @GetMapping("/getEngineSn/{firmId}/{engineId}")
    String getEngineSn(@PathVariable int firmId, @PathVariable int engineId) {
        return engineService.getEngineSn(firmId, engineId);
    }
}
