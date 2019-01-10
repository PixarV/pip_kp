package com.itmo.dao;

import com.pip.entities.Ammunition;
import com.pip.entities.Weapon;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class WeaponDao extends CommonDao<Weapon> {
    public WeaponDao() {
        super(Weapon.class);
    }

    public int custom_update(Weapon weapon) {
        Query query = entityManager.createQuery("update Weapon c set c.callibr = :callibr, " +
                "c.title = :title, " +
                "c.weight = :weight " +
                "WHERE c.id = :id");
        return query.setParameter("callibr", weapon.getCallibr())
                .setParameter("title", weapon.getTitle())
                .setParameter("weight", weapon.getWeight())
                .setParameter("id", weapon.getId()).executeUpdate();
    }

    public int removeWeaponById(int id) {
        Query query = entityManager.createQuery("DELETE FROM Weapon m WHERE m.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }

    public int removeWeaponFromMtoM(int weaponId) {
        Query query = entityManager.createNativeQuery("DELETE FROM ammunition_weapon WHERE id_weapon=?");
        return query.setParameter(1, weaponId).executeUpdate();
    }

    public List<Ammunition> getAmmunition(int weaponId) {
        Query query = entityManager.createQuery("select a from Ammunition a left join a.weapons t where t.id=:id");
        query.setParameter("id", weaponId);
        return query.getResultList();
    }
}

