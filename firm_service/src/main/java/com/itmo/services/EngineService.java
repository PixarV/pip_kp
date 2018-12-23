package com.itmo.services;

import com.pip.entities.Engine;
import com.itmo.dao.EngineDao;
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
