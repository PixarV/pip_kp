package com.itmo.contollers;

import com.itmo.services.ModelService;
import com.pip.entities.Model;
import com.pip.enums.TypeFuel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Controller
@AllArgsConstructor
@RequestMapping("/models")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ModelController {

    ModelService modelService;

    @ResponseBody
    @GetMapping("/getAll")
    List<Model> findAllModels() {
        return modelService.findAllModels();
    }

    @ResponseBody
    @PostMapping("/add")
    Model addModel(@RequestBody Model model) {
        modelService.addModel(model);
        return model;
    }

    @ResponseBody
    @GetMapping("/get/{modelId}")
    Model getModel(@PathVariable int modelId) {
        return modelService.getModelById(modelId);
    }

    @ResponseBody
    @PostMapping("/update")
    Model updateModel(@RequestBody Model model) {
        return modelService.updateModel(model);
    }

    @ResponseBody
    @DeleteMapping("/delete")
    void deleteModel(@RequestBody Model model) {
        modelService.deleteModel(model);
    }

    @ResponseBody
    @DeleteMapping("/delete/{modelId}")
    void deleteModelById(@PathVariable int modelId) {
        modelService.deleteModelById(modelId);
    }


}
