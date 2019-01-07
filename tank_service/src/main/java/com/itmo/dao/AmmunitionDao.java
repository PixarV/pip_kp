package com.itmo.dao;

import com.pip.entities.Ammunition;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository
@Transactional
public class AmmunitionDao extends CommonDao<Ammunition> {
    public AmmunitionDao() {
        super(Ammunition.class);
    }

    public int custom_update(Ammunition ammunition) {
        Query query = entityManager.createQuery("update Ammunition a set a.callibr = :callibr, " +
                "a.type = :amm_type, " +
                "a.breakage = :breakage WHERE a.id = :id");
        return query.setParameter("callibr", ammunition.getCallibr())
                .setParameter("amm_type", ammunition.getType())
                .setParameter("breakage", ammunition.getBreakage())
                .setParameter("id", ammunition.getId()).executeUpdate();
    }

    public int removeAmmunitionById(int id) {
        Query query = entityManager.createQuery("DELETE FROM Ammunition m WHERE m.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }
}

