package com.itmo.services;

import com.itmo.dao.FirmEngineDao;
import com.itmo.dao.FirmTowerDao;
import com.itmo.dao.FirmWeaponDao;
import com.pip.entities.Engine;
import com.pip.entities.Tower;
import com.pip.entities.Weapon;
import com.pip.enums.Approve;
import com.pip.entities.Firm;
import com.itmo.dao.FirmDao;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.pip.enums.Approve.APPROVED;
import static com.pip.enums.Approve.NOT_APPROVED;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class FirmService {

    FirmDao firmDao;
    FirmEngineDao firmEngineDao;
    FirmTowerDao firmTowerDao;
    FirmWeaponDao firmWeaponDao;

    public List<Firm> getAllFirms() {
        return firmDao.findAllEntities();
    }

    public void addFirm(Firm firm) {
        if (Objects.isNull(firm.getStatus()) || firm.getStatus() == APPROVED)
            firm.setStatus(NOT_APPROVED);

        firmDao.saveEntity(firm);
    }

    public void deleteFirm(Firm firm) {
        firmDao.removeEntity(firm);
    }

    public int deleteFirmById(int firmId) {
        return firmDao.removeFirmById(firmId);
    }

    public Firm getFirmById(int firmId) {
        return firmDao.findEntityById(firmId);
    }

    public void updateFirm(Firm firm) {
        firmDao.custom_update(firm);
    }

    public void changeStatus(int firmId, Approve status) {
        firmDao.changeStatus(firmId, status);
    }

    public void removeFirmEngineFromMtoM(int firmId, int engineId) {
        firmEngineDao.removeFirmEngine(firmId, engineId);
    }

    public void removeFirmFromFtoE(int firmId) {
        firmEngineDao.removeFirmFromFtoE(firmId);
    }

    public void removeFirmTowerFromMtoM(int firmId, int towerId) {
        firmTowerDao.removeFirmTower(firmId, towerId);
    }

    public void removeFirmFromFtoT(int firmId) {
        firmTowerDao.removeFirmFromFtoT(firmId);
    }

    public void removeFirmWeaponFromMtoM(int firmId, int weaponId) {
        firmWeaponDao.removeFirmWeapon(firmId, weaponId);
    }

    public void removeFirmFromFtoW(int firmId) {
        firmWeaponDao.removeFirmFromFtoW(firmId);
    }

    public List<Engine> getAllEngines(int firmId) {
        Firm firm = firmDao.findEntityById(firmId);
        return firmEngineDao.getAllEngines(firm);
    }

    public List<Tower> getAllTowers(int firmId) {
        Firm firm = firmDao.findEntityById(firmId);
        return firmTowerDao.getAllTowers(firm);
    }

    public List<Weapon> getAllWeapons(int firmId) {
        Firm firm = firmDao.findEntityById(firmId);
        return firmWeaponDao.getAllWeapons(firm);
    }
}
