package com.itmo.contollers;

import com.itmo.services.ModelService;
import com.pip.entities.Engine;
import com.pip.entities.Model;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@AllArgsConstructor
@RequestMapping("/models")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ModelController {

    ModelService modelService;

    @GetMapping("/getAll")
    List<Model> findAllModels() {
        return modelService.getAllModels();
    }

    @PostMapping("/add")
    Model addModel(@RequestBody Model model) {
        modelService.addModel(model);
        return model;
    }

    @GetMapping("/get/{modelId}")
    Model getModel(@PathVariable int modelId) {
        return modelService.getModelById(modelId).get(0);
    }

    @PutMapping("/update")
    void updateModel(@RequestBody Model model) {
        modelService.updateModel(model);
    }

    @DeleteMapping("/delete")
    void deleteModel(@RequestBody Model model) {
        modelService.deleteModel(model);
    }

    @DeleteMapping("/delete/{modelId}")
    void deleteModelById(@PathVariable int modelId) {
        modelService.deleteModelById(modelId);
    }

    @PutMapping("/addEngine/{engineId}")
    void addEngineToModel(@RequestBody Model model,
                           @PathVariable int engineId) {
        modelService.addEngine(model.getId(), engineId);
    }

    @PutMapping("/removeModelEngine/{modelId}")
    void removeModelFromMtoM(@PathVariable int modelId) {
        modelService.removeModelFromMtoM(modelId);
    }

    @GetMapping("/getEngines/{modelId}")
    List<Engine> getEngines(@PathVariable int modelId) {
        return modelService.getEngines(modelId);
    }
}
