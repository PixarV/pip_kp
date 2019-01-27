package com.itmo.services;

import com.itmo.dao.EngineDao;
import com.itmo.dao.FirmDao;
import com.itmo.dao.FirmEngineDao;
import com.pip.entities.Engine;
import com.pip.entities.Firm;
import com.pip.entities.FirmEngine;
import com.pip.entities.Model;
import com.pip.enums.Approve;
import com.pip.enums.FirmSpecializtion;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pip.enums.Approve.NOT_APPROVED;
import static com.pip.enums.FirmSpecializtion.TANK;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class EngineService {

    EngineDao engineDao;
    FirmDao firmDao;
    FirmEngineDao firmEngineDao;

    public List<Engine> findAllEngines() {
        return engineDao.findAllEntities();
    }

    public void addEngine(Engine engine) {
        Firm firm = (Firm) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (firm.getSpecialization() == TANK) {
            throw new IllegalStateException("Specialization of firm is incorrect");
        }

        if (firm.getStatus() == NOT_APPROVED) {
            throw new IllegalStateException("Firm is not approved");
        }

        engineDao.saveEntity(engine);
        FirmEngine firmEngine = FirmEngine.builder()
                .engine(engine)
                .firm(firm)
                .build();
        firmEngineDao.saveEntity(firmEngine);
        engine.setPower(0);
        engine.setWeight(0);
        engine.setTitle("");

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

    public void updateEngine(Engine tempEngine) {
        Engine engine = engineDao.findEntityById(tempEngine.getId());
        if (!tempEngine.getTitle().equals("")) {
            engine.setTitle(tempEngine.getTitle());
        }
        if (tempEngine.getPower() != 0) {
            engine.setPower(tempEngine.getPower());
        }
        if (tempEngine.getWeight() != 0) {
            engine.setWeight(tempEngine.getWeight());
        }
        engineDao.custom_update(engine);
        engineDao.removeEngineFromMtoM(tempEngine.getId());
        tempEngine.setPower(0);
        tempEngine.setWeight(0);
        tempEngine.setTitle("");
    }

    public void removeModelFromMtoM(int engineId) {
        engineDao.removeEngineFromMtoM(engineId);
    }

    public List<Model> getModels(int engineId) {
        return engineDao.getModels(engineId);
    }

    public String getEngineSn(int engineId) {
        Firm firm = (Firm) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return firmEngineDao.getEngineSn(firm.getId(), engineId);
    }

    public List<Firm> getFirms(int engineId) {
        Engine engine = engineDao.findEntityById(engineId);
        return engineDao.getFirms(engine);
    }
}
