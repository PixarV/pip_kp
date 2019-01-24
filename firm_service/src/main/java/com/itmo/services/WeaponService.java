package com.itmo.services;

import com.itmo.dao.FirmDao;
import com.itmo.dao.FirmWeaponDao;
import com.itmo.dao.WeaponDao;
import com.pip.entities.*;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public void addWeapon(Weapon weapon) {
        Firm firm = (Firm) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
        weapon.setCallibr(0);
        weapon.setTitle("");
        weapon.setWeight(0);
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
        weaponDao.removeWeaponFromMtoMAW(weapon.getId());
        weaponDao.removeWeaponFromMtoMTW(weapon.getId());
        weapon.setCallibr(0);
        weapon.setTitle("");
        weapon.setWeight(0);
    }

    public void removeWeaponFromMtoMAW(int weaponId) {
        weaponDao.removeWeaponFromMtoMAW(weaponId);
    }

    public List<Ammunition> getAmmunition(int weaponId) {
        return weaponDao.getAmmunition(weaponId);
    }

    public void removeWeaponFromMtoMTW(int weaponId) {
        weaponDao.removeWeaponFromMtoMTW(weaponId);
    }

    public List<Tower> getTowers(int weaponId) {
        return weaponDao.getTowers(weaponId);
    }

    public String getWeaponSn(int weaponId) {
        Firm firm = (Firm) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return firmWeaponDao.getWeaponSn(firm.getId(), weaponId);
    }

    public List<Firm> getFirms(int weaponId) {
        Weapon weapon = weaponDao.findEntityById(weaponId);
        return weaponDao.getFirms(weapon);
    }
}
