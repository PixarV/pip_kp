package com.itmo.services;

import com.itmo.dao.ChassisDao;
import com.pip.entities.Chassis;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ChassisService {

    ChassisDao chassisDao;

    public List<Chassis> findAllChassiss() {
        return chassisDao.findAllEntities();
    }

    public void addChassis(Chassis chassis) {
        chassisDao.saveEntity(chassis);
    }

    public void deleteChassis(Chassis chassis) {
        chassisDao.removeEntity(chassis);
    }

    public int deleteChassisById(int chassisId) {
        return chassisDao.removeChassisById(chassisId);
    }

    public Chassis getChassisById(int chassisId) {
        return chassisDao.findEntityById(chassisId);
    }

    public Chassis updateChassis(Chassis chassis) {
        return chassisDao.updateEntity(chassis);
    }
}