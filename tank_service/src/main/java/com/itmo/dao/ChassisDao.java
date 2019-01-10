package com.itmo.dao;

import com.pip.entities.Chassis;
import com.pip.entities.Tower;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ChassisDao extends CommonDao<Chassis> {
    public ChassisDao() {
        super(Chassis.class);
    }

    public int custom_update(Chassis chassis) {
        Query query = entityManager.createQuery("update Chassis c set c.model = :model, " +
                "c.title = :title, " +
                "c.carring = :carring, " +
                "c.turnSpeed = :turnSpeed, " +
                "c.weight = :weight " +
                "WHERE c.id = :id");
        return query.setParameter("model", chassis.getModel())
                .setParameter("title", chassis.getTitle())
                .setParameter("carring", chassis.getCarring())
                .setParameter("turnSpeed", chassis.getTurnSpeed())
                .setParameter("weight", chassis.getWeight())
                .setParameter("id", chassis.getId()).executeUpdate();
    }

    public int removeChassisById(int id) {
        Query query = entityManager.createQuery("DELETE FROM Chassis m WHERE m.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }

    public int addTower(int chassisId, int towerId) {
        Query query = entityManager.createNativeQuery("INSERT INTO chassis_tower VALUES(?, ?)");
        return query.setParameter(1, chassisId)
                .setParameter(2, towerId).executeUpdate();
    }

    public int removeChassisFromMtoM(int chassisId) {
        Query query = entityManager.createNativeQuery("DELETE FROM chassis_tower WHERE id_chassis=?");
        return query.setParameter(1, chassisId).executeUpdate();
    }

    public List<Tower> getTowers(int chassisId) {
        Query query = entityManager.createQuery("select a from Tower a left join a.chassis t where t.id=:id");
        query.setParameter("id", chassisId);
        return query.getResultList();
    }
}

