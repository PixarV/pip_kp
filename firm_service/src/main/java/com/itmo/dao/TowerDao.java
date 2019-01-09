package com.itmo.dao;

import com.pip.entities.Tower;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository
@Transactional
public class TowerDao extends CommonDao<Tower> {
    public TowerDao() {
        super(Tower.class);
    }

    public int custom_update(Tower tower) {
        Query query = entityManager.createQuery("update Tower c set c.armor = :armor, " +
                "c.title = :title, " +
                "c.viewRadius = :viewRadius, " +
                "c.turnSpeed = :turnSpeed, " +
                "c.weight = :weight " +
                "WHERE c.id = :id");
        return query.setParameter("armor", tower.getArmor())
                .setParameter("title", tower.getTitle())
                .setParameter("viewRadius", tower.getViewRadius())
                .setParameter("turnSpeed", tower.getTurnSpeed())
                .setParameter("weight", tower.getWeight())
                .setParameter("id", tower.getId()).executeUpdate();
    }

    public int removeTowerById(int id) {
        Query query = entityManager.createQuery("DELETE FROM Tower m WHERE m.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }

    public int removeTowerFromMtoM(int towerId) {
        Query query = entityManager.createNativeQuery("DELETE FROM chassis_tower WHERE id_tower=?");
        return query.setParameter(1, towerId).executeUpdate();
    }
}

