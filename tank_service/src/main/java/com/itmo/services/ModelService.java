package com.itmo.services;

import com.itmo.dao.ModelDao;
import com.pip.entities.Engine;
import com.pip.entities.Model;
import com.pip.entities.Monster;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.orm.jpa.JpaSystemException;
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
        } catch (JpaSystemException e) {
            String errorMessage = e.getCause().getCause().getMessage();
            String outputError = errorMessage.substring(0, errorMessage.indexOf("Where"));
            FacesContext
                    .getCurrentInstance()
                    .addMessage("addModelForm:addModelButton", // id ratget form and target element
                            new FacesMessage("Error :(", outputError));
        } catch (Exception e) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage("addModelForm:addModelButton", // id ratget form and target element
                            new FacesMessage("на всякий :(", e.getMessage()));
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

    public void addEngine(int modelId, int engineId) {
        try {
            modelDao.addEngine(modelId, engineId);
        } catch (JpaSystemException e) {
            String errorMessage = e.getCause().getCause().getMessage();
            String outputError = errorMessage.substring(0, errorMessage.indexOf("Where"));
            FacesContext
                    .getCurrentInstance()
                    .addMessage("addModEngForm:addModEngButton", // id ratget form and target element
                            new FacesMessage("Error :(", outputError));
        } catch (Exception e) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage("addModEngForm:addModEngButton", // id ratget form and target element
                            new FacesMessage("на всякий :(", e.getMessage()));
        }
    }

    public void removeModelFromMtoM(int modelId) {
        modelDao.removeModelFromMtoM(modelId);
    }

    public List<Engine> getEngines(int modelId) {
        return modelDao.getEngines(modelId);
    }

    public List<Monster> getAllModelEngine() {
        return modelDao.getAllModelEngine();
    }
}
