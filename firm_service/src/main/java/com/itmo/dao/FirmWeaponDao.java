package com.itmo.dao;

import com.pip.entities.FirmWeapon;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository
@Transactional
public class FirmWeaponDao extends CommonDao<FirmWeapon> {
    public FirmWeaponDao() {
        super(FirmWeapon.class);
    }

    public int removeFirmWeapon(int firmId, int weaponId) {
        Query query = entityManager.createQuery("DELETE FROM FirmWeapon m " +
                "WHERE m.id_firm = :firm AND m.id_weapon = :weapon");
        return query.setParameter("firm", firmId)
                .setParameter("weapon", weaponId)
                .executeUpdate();
    }

    public int removeFirmFromFtoW(int firmId) {
        Query query = entityManager.createQuery("DELETE FROM FirmWeapon m " +
                "WHERE m.id_firm = :firm");
        return query.setParameter("firm", firmId)
                .executeUpdate();
    }

    public String getWeaponSn(int firmId, int weaponId) {
        Query query = entityManager.createNativeQuery("SELECT serial_no FROM firm_weapon " +
                "WHERE id_firm = ? AND id_weapon = ?");
        return (String) query.setParameter(1, firmId)
                .setParameter(2, weaponId)
                .getSingleResult();
    }
}
