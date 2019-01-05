package com.itmo.dao;

import com.pip.entities.Ammunition;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class AmmunitionDao extends CommonDao<Ammunition> {
    public AmmunitionDao() {
        super(Ammunition.class);
    }

    public int removeAmmunitionById(int id) {
        Query query = entityManager.createQuery("DELETE FROM Ammunition m WHERE m.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }
}

