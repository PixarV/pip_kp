package com.itmo.dao;

import com.pip.entities.Model;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class ModelDao extends CommonDao<Model> {
    public ModelDao() {
        super(Model.class);
    }

    public int removeModelById(int id) {
        Query query = entityManager.createQuery("DELETE FROM Model m WHERE m.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }
}

