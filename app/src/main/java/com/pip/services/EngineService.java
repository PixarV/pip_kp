package com.pip.services;

import com.pip.dao.EngineDao;
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
}
