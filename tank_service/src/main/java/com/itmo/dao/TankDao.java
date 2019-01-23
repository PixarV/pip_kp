package com.itmo.dao;

import com.pip.entities.Tank;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository
@Transactional
public class TankDao extends CommonDao<Tank> {
    public TankDao() {
        super(Tank.class);
    }

    public int custom_update(Tank tank) {
        Query query = entityManager.createQuery("update Tank c set c.idModel = :id_model, " +
                "c.idChassis = :id_chassis, " +
                "c.snEngine = :sn_engine, " +
                "c.snWeapon = :sn_weapon, " +
                "c.snTower = :sn_tower " +
                "WHERE c.id = :id");
        return query.setParameter("id_model", tank.getIdModel())
                .setParameter("id_chassis", tank.getIdChassis())
                .setParameter("sn_engine", tank.getSnEngine())
                .setParameter("sn_weapon", tank.getSnWeapon())
                .setParameter("sn_tower", tank.getSnTower())
                .setParameter("id", tank.getId()).executeUpdate();
    }

    public int removeTankById(int id) {
        Query query = entityManager.createQuery("DELETE FROM Tank m WHERE m.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }
}
