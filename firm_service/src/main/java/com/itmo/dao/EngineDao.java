package com.itmo.dao;

import com.pip.entities.Engine;
import com.pip.entities.Engine;
import com.pip.entities.Firm;
import com.pip.entities.Model;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
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

    public int removeEngineFromMtoM(int engineId) {
        Query query = entityManager.createNativeQuery("DELETE FROM model_engine WHERE id_engine=?");
        return query.setParameter(1, engineId).executeUpdate();
    }

    public List<Model> getModels(int engineId) {
        Query query = entityManager.createQuery("select a from Model a left join a.engines t where t.id=:id");
        query.setParameter("id", engineId);
        return query.getResultList();
    }

    public List<Firm> getFirms(Engine engine) {
        Query query = entityManager.createQuery("select a from Firm a left join a.engines t where t.engine=:engine");
        query.setParameter("engine", engine);
        return query.getResultList();
    }
}

