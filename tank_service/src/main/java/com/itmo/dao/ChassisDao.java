package com.itmo.dao;

import com.pip.entities.Chassis;
import com.pip.entities.Chassis;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

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
}

