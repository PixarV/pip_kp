package com.itmo.contollers;

import com.itmo.services.ModelService;
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
        return modelService.findAllModels();
    }

    @PostMapping("/add")
    Model addModel(@RequestBody Model model) {
        modelService.addModel(model);
        return model;
    }

    @GetMapping("/get/{modelId}")
    Model getModel(@PathVariable int modelId) {
        return modelService.getModelById(modelId);
    }

    @PutMapping("/update")
    Model updateModel(@RequestBody Model model) {
        return modelService.updateModel(model);
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
    Model addEngineToModel(@RequestBody Model model,
                           @PathVariable int engineId) {
        return modelService.addEngine(model, engineId);
    }
}
