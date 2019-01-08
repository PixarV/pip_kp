package com.itmo.dao;

import com.pip.entities.Engine;
import com.pip.entities.Engine;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class EngineDao extends CommonDao<Engine> {
    public EngineDao() {
        super(Engine.class);
    }

    public int custom_update(Engine engine) {
        Query query = entityManager.createQuery("update Engine c set c.fuel = :fuel, " +
                "c.title = :title, " +
                "c.power = :power, " +
                "c.weight = :weight " +
                "WHERE c.id = :id");
        return query.setParameter("fuel", engine.getFuel())
                .setParameter("title", engine.getTitle())
                .setParameter("power", engine.getPower())
                .setParameter("weight", engine.getWeight())
                .setParameter("id", engine.getId()).executeUpdate();
    }

    public int removeEngineById(int id) {
        Query query = entityManager.createQuery("DELETE FROM Engine m WHERE m.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }
}

