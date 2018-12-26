package com.itmo.dao;

import com.pip.entities.Chassis;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class ChassisDao extends CommonDao<Chassis> {
    public ChassisDao() {
        super(Chassis.class);
    }

    public int removeChassisById(int id) {
        Query query = entityManager.createQuery("DELETE FROM Chassis m WHERE m.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }
}

