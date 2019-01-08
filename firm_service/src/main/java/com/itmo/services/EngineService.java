package com.itmo.services;

import com.itmo.dao.EngineDao;
import com.itmo.dao.EngineDao;
import com.pip.entities.Engine;
import com.pip.entities.Engine;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class EngineService {

    EngineDao engineDao;

    public List<Engine> findAllEngines() {
        return engineDao.findAllEntities();
    }

    public void addEngine(Engine engine) {
        engineDao.saveEntity(engine);
    }

    public void deleteEngine(Engine engine) {
        engineDao.removeEntity(engine);
    }

    public int deleteEngineById(int engineId) {
        return engineDao.removeEngineById(engineId);
    }

    public Engine getEngineById(int engineId) {
        return engineDao.findEntityById(engineId);
    }

    public void updateEngine(Engine engine) {
        engineDao.custom_update(engine);
    }
}
