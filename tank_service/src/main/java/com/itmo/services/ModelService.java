package com.itmo.services;

import com.itmo.dao.ModelDao;
import com.pip.entities.Engine;
import com.pip.entities.Model;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.*;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ModelService {

    ModelDao modelDao;

    public List<Model> getAllModels() {
        return modelDao.findAllEntities();
    }

    public void addModel(Model model) {
        try {
            Model tempModel = model.withId(0);
            modelDao.saveEntity(tempModel);
        } catch (Exception e) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage("addModelForm:addModelButton", // id ratget form and target element
                            new FacesMessage("Error :(", e.getMessage()));
        }
    }

    public void deleteModel(Model model) {
        modelDao.removeEntity(model);
    }

    public int deleteModelById(int modelId) {
        return modelDao.removeModelById(modelId);
    }

    public List<Model> getModelById(int modelId) {
        if (modelId != 0) {
            Model tempModel = modelDao.findEntityById(modelId);
            if (tempModel != null)
                return Arrays.asList(tempModel);
            else {
                FacesContext
                        .getCurrentInstance()
                        .addMessage("shortModelGetForm:shortModelGetButton", // id ratget form and target element
                                new FacesMessage("Error :(", "Can't find entry"));
            }
        }
        return new ArrayList<>();
    }

    public void updateModel(Model tempModel) {
        Model model = modelDao.findEntityById(tempModel.getId());
        if (!tempModel.getTitle().equals("")) {
            model.setTitle(tempModel.getTitle());
        }
        if (!tempModel.getArmor().equals("")) {
            model.setArmor(tempModel.getArmor());
        }
        if (tempModel.getMaxSpeedBackward() != 0) {
            model.setMaxSpeedBackward(tempModel.getMaxSpeedBackward());
        }
        if (tempModel.getMaxSpeedForward() != 0) {
            model.setMaxSpeedForward(tempModel.getMaxSpeedForward());
        }

        modelDao.custom_update(model);
        modelDao.removeModelFromMtoM(model.getId());

        tempModel.setArmor("");
        tempModel.setMaxSpeedBackward(0);
        tempModel.setMaxSpeedForward(0);
        tempModel.setTitle("");
    }

    public void addEngine(Model model, int engineId) {
        modelDao.addEngine(model.getId(), engineId);
    }

    public void removeModelFromMtoM(int modelId) {
        modelDao.removeModelFromMtoM(modelId);
    }

    public List<Engine> getEngines(int modelId) {
        return modelDao.getEngines(modelId);
    }
}
