package com.itmo.dao;

import com.pip.entities.Model;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
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
}

