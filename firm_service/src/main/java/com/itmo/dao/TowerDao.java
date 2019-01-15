package com.itmo.dao;

import com.pip.entities.Chassis;
import com.pip.entities.Firm;
import com.pip.entities.Tower;
import com.pip.entities.Weapon;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

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

    public int removeTowerFromMtoMCT(int towerId) {
        Query query = entityManager.createNativeQuery("DELETE FROM chassis_tower WHERE id_tower=?");
        return query.setParameter(1, towerId).executeUpdate();
    }

    public List<Chassis> getChassis(int towerId) {
        Query query = entityManager.createQuery("select a from Chassis a left join a.towers t where t.id=:id");
        query.setParameter("id", towerId);
        return query.getResultList();
    }

    public int addWeapon(int towerId, int weaponId) {
        Query query = entityManager.createNativeQuery("INSERT INTO tower_weapon VALUES(?, ?)");
        return query.setParameter(1, towerId)
                .setParameter(2, weaponId).executeUpdate();
    }

    public int removeTowerFromMtoMTW(int towerId) {
        Query query = entityManager.createNativeQuery("DELETE FROM tower_weapon WHERE id_tower=?");
        return query.setParameter(1, towerId).executeUpdate();
    }

    public List<Weapon> getWeapons(int towerId) {
        Query query = entityManager.createQuery("select a from Weapon a left join a.towers t where t.id=:id");
        query.setParameter("id", towerId);
        return query.getResultList();
    }

    public List<Firm> getFirms(Tower tower) {
        Query query = entityManager.createQuery("select a from Firm a left join a.towers t where t.tower=:tower");
        query.setParameter("tower", tower);
        return query.getResultList();
    }
}

