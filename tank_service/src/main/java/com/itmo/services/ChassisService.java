package com.itmo.services;

import com.itmo.dao.ChassisDao;
import com.pip.entities.Chassis;
import com.pip.entities.Tower;
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

    public void updateChassis(Chassis chassis) {
        chassisDao.custom_update(chassis);
        chassisDao.removeChassisFromMtoM(chassis.getId());
    }

    public void addTower(Chassis chassis, int towerId) {
        chassisDao.addTower(chassis.getId(), towerId);
    }

    public void removeChassisFromMtoM(int chassisId) {
        chassisDao.removeChassisFromMtoM(chassisId);
    }

    public List<Tower> getTowers(int chassisId) {
        return chassisDao.getTowers(chassisId);
    }
}
