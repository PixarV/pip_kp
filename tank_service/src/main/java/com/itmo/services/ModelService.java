package com.itmo.services;

import com.itmo.dao.EngineDao;
import com.itmo.dao.ModelDao;
import com.pip.entities.Engine;
import com.pip.entities.Model;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.*;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ModelService {

    ModelDao modelDao;
    EngineDao engineDao;

    public List<Model> findAllModels() {
        return modelDao.findAllEntities();
    }

    public void addModel(Model model) {
        modelDao.saveEntity(model);
    }

    public void deleteModel(Model model) {
        modelDao.removeEntity(model);
    }

    public int deleteModelById(int modelId) {
        return modelDao.removeModelById(modelId);
    }

    public Model getModelById(int modelId) {
        return modelDao.findEntityById(modelId);
    }

    public void updateModel(Model model) {
        modelDao.custom_update(model);
    }

    public Model addEngine(Model model, int engineId) {
        modelDao.detachEntity(model);
        Engine engine = engineDao.findEntityById(engineId);
        Set<Engine> engines = model.getEngines();
        if (!Objects.nonNull(engines)) engines = new HashSet<>();
        engines.add(engine);
        modelDao.refreshEntity(model);
        modelDao.mergeEntity(model);
        return model;
    }
}
