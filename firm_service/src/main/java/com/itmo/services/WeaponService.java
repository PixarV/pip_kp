package com.itmo.services;

import com.itmo.dao.FirmDao;
import com.itmo.dao.FirmWeaponDao;
import com.itmo.dao.WeaponDao;
import com.pip.entities.Ammunition;
import com.pip.entities.Firm;
import com.pip.entities.FirmWeapon;
import com.pip.entities.Weapon;
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
public class WeaponService {

    WeaponDao weaponDao;
    FirmDao firmDao;
    FirmWeaponDao firmWeaponDao;

    public List<Weapon> findAllWeapons() {
        return weaponDao.findAllEntities();
    }

    public void addWeapon(Weapon weapon, int firmId) {
        Firm firm = firmDao.findEntityById(firmId);
        if (firm.getSpecialization() == ENGINE) {
            throw new IllegalStateException("Specialization of firm is incorrect");
        }

        if (firm.getStatus() == NOT_APPROVED) {
            throw new IllegalStateException("Firm is not approved");
        }
        weaponDao.saveEntity(weapon);
        FirmWeapon firmWeapon = FirmWeapon.builder()
                .weapon(weapon)
                .firm(firm)
                .build();
        firmWeaponDao.saveEntity(firmWeapon);
    }

    public void deleteWeapon(Weapon weapon) {
        weaponDao.removeEntity(weapon);
    }

    public int deleteWeaponById(int weaponId) {
        return weaponDao.removeWeaponById(weaponId);
    }

    public Weapon getWeaponById(int weaponId) {
        return weaponDao.findEntityById(weaponId);
    }

    public void updateWeapon(Weapon weapon) {
        weaponDao.custom_update(weapon);
        weaponDao.removeWeaponFromMtoM(weapon.getId());
    }

    public void removeModelFromMtoM(int weaponId) {
        weaponDao.removeWeaponFromMtoM(weaponId);
    }

    public List<Ammunition> getAmmunition(int weaponId) {
        return weaponDao.getAmmunition(weaponId);
    }

    public String getWeaponSn(int firmId, int weaponId) {
        return firmWeaponDao.getWeaponSn(firmId, weaponId);
    }
}
