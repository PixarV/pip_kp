package com.itmo.dao;

import com.pip.entities.FirmTower;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository
@Transactional
public class FirmTowerDao extends CommonDao<FirmTower> {
    public FirmTowerDao() {
        super(FirmTower.class);
    }

    public int removeFirmTower(int firmId, int towerId) {
        Query query = entityManager.createQuery("DELETE FROM FirmTower m " +
                "WHERE m.id_firm = :firm AND m.id_tower = :tower");
        return query.setParameter("firm", firmId)
                .setParameter("tower", towerId)
                .executeUpdate();
    }

    public int removeFirmFromFtoT(int firmId) {
        Query query = entityManager.createQuery("DELETE FROM FirmTower m " +
                "WHERE m.id_firm = :firm");
        return query.setParameter("firm", firmId)
                .executeUpdate();
    }

    public String getTowerSn(int firmId, int towerId) {
        Query query = entityManager.createNativeQuery("SELECT serial_no FROM firm_tower " +
                "WHERE id_firm = ? AND id_tower = ?");
        return (String) query.setParameter(1, firmId)
                .setParameter(2, towerId)
                .getSingleResult();
    }
}
