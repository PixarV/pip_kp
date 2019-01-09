package com.itmo.services;

import com.itmo.dao.WeaponDao;
import com.pip.entities.Weapon;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class WeaponService {

    WeaponDao weaponDao;

    public List<Weapon> findAllWeapons() {
        return weaponDao.findAllEntities();
    }

    public void addWeapon(Weapon weapon) {
        weaponDao.saveEntity(weapon);
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
    }

    public void removeModelFromMtoM(int weaponId) {
        weaponDao.removeWeaponFromMtoM(weaponId);
    }
}
