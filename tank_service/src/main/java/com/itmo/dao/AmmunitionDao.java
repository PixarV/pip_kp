package com.itmo.dao;

import com.pip.entities.Ammunition;
import com.pip.entities.Monster;
import com.pip.entities.Weapon;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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

    public int addWeapon(int ammunitionId, int weaponId) {
        Query query = entityManager.createNativeQuery("INSERT INTO ammunition_weapon VALUES(?, ?)");
        return query.setParameter(1, ammunitionId)
                .setParameter(2, weaponId).executeUpdate();
    }

    public int removeAmmunitionFromMtoM(int ammunitionId) {
        Query query = entityManager.createNativeQuery("DELETE FROM ammunition_weapon WHERE id_ammunition=?");
        return query.setParameter(1, ammunitionId).executeUpdate();
    }

    public List<Weapon> getWeapons(int ammunitionId) {
        Query query = entityManager.createQuery("select a from Weapon a left join a.ammunition t where t.id=:id");
        query.setParameter("id", ammunitionId);
        return query.getResultList();
    }

    public List<Monster> getAllAmunitionWeapon() {
        Query query = entityManager.createNativeQuery("select id_ammunition, id_weapon, e.title as one, e.title as two from ammunition_weapon join ammunition as m on id_ammunition=m.id join weapon as e on id_weapon=e.id;");
        List<Object[]> list = query.getResultList();

        List<Monster> result = new ArrayList<>();
        for (Object[] elem : list) {
            int modelId = (Integer) elem[0];
            int engineId = (Integer) elem[1];
            String modelTitle = (String) elem[2];
            String engineTitle = (String) elem[3];
            Monster monster = Monster.builder()
                    .amunitionId(modelId)
                    .weaponId(engineId)
                    .ammunitionTitle(modelTitle)
                    .weaponTitle(engineTitle)
                    .build();
            result.add(monster);
        }

        return result;
    }
}

