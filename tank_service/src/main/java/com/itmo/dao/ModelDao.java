package com.itmo.dao;

import com.pip.entities.Engine;
import com.pip.entities.Model;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class ModelDao extends CommonDao<Model> {
    public ModelDao() {
        super(Model.class);
    }

    public int custom_update(Model model) {
        Query query = entityManager.createQuery("update Model c set c.type = :model_type, " +
                "c.title = :title, " +
                "c.maxSpeedForward = :maxSpeedForward, " +
                "c.maxSpeedBackward = :maxSpeedBackward, " +
                "c.armor = :armor " +
                "WHERE c.id = :id");
        return query.setParameter("model_type", model.getType())
                .setParameter("title", model.getTitle())
                .setParameter("maxSpeedForward", model.getMaxSpeedForward())
                .setParameter("maxSpeedBackward", model.getMaxSpeedBackward())
                .setParameter("armor", model.getArmor())
                .setParameter("id", model.getId()).executeUpdate();
    }

    public int removeModelById(int id) {
        Query query = entityManager.createQuery("DELETE FROM Model m WHERE m.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }

    public int addEngine(int modelId, int engineId) {
        Query query = entityManager.createNativeQuery("INSERT INTO model_engine VALUES(?, ?)");
        return query.setParameter(1, modelId)
                .setParameter(2, engineId).executeUpdate();
    }

    public int removeModelFromMtoM(int modelId) {
        Query query = entityManager.createNativeQuery("DELETE FROM model_engine WHERE id_model=?");
        return query.setParameter(1, modelId).executeUpdate();
    }

    public List<Engine> getEngines(int modelId) {
        Query query = entityManager.createQuery("select a from Engine a left join a.models t where t.id=:id");
        query.setParameter("id", modelId);
        return query.getResultList();
    }
}

