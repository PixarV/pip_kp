package com.itmo.dao;

import com.pip.entities.Engine;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class EngineDao extends CommonDao<Engine> {
    public EngineDao() {
        super(Engine.class);
    }

    public int removeEngineById(int id) {
        Query query = entityManager.createQuery("DELETE FROM Engine m WHERE m.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }
}

