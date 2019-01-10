package com.itmo.services;

import com.itmo.dao.TowerDao;
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
public class TowerService {

    TowerDao towerDao;

    public List<Tower> findAllTowers() {
        return towerDao.findAllEntities();
    }

    public void addTower(Tower tower) {
        towerDao.saveEntity(tower);
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
}
