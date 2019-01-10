package com.itmo.services;

import com.itmo.dao.FirmDao;
import com.itmo.dao.FirmTowerDao;
import com.itmo.dao.TowerDao;
import com.pip.entities.Chassis;
import com.pip.entities.Firm;
import com.pip.entities.FirmTower;
import com.pip.entities.Tower;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pip.enums.Approve.NOT_APPROVED;
import static com.pip.enums.FirmSpecializtion.ENGINE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TowerService {

    TowerDao towerDao;
    FirmDao firmDao;
    FirmTowerDao firmTowerDao;

    public List<Tower> findAllTowers() {
        return towerDao.findAllEntities();
    }

    public void addTower(Tower tower, int firmId) {
        Firm firm = firmDao.findEntityById(firmId);
        if (firm.getSpecialization() == ENGINE) {
            throw new IllegalStateException("Specialization of firm is incorrect");
        }

        if (firm.getStatus() == NOT_APPROVED) {
            throw new IllegalStateException("Firm is not approved");
        }
        towerDao.saveEntity(tower);
        FirmTower firmTower = FirmTower.builder()
                .tower(tower)
                .firm(firm)
                .build();
        firmTowerDao.saveEntity(firmTower);
    }

    public void deleteTower(Tower tower) {
        towerDao.removeEntity(tower);
    }

    public int deleteTowerById(int towerId) {
        return towerDao.removeTowerById(towerId);
    }

    public Tower getTowerById(int towerId) {
        return towerDao.findEntityById(towerId);
    }

    public void updateTower(Tower tower) {
        towerDao.custom_update(tower);
        towerDao.removeTowerFromMtoM(tower.getId());
    }

    public void removeModelFromMtoM(int towerId) {
        towerDao.removeTowerFromMtoM(towerId);
    }

    public List<Chassis> getChassis(int towerId) {
        return towerDao.getChassis(towerId);
    }

    public String getTowerSn(int firmId, int towerId) {
        return firmTowerDao.getTowerSn(firmId, towerId);
    }
}
