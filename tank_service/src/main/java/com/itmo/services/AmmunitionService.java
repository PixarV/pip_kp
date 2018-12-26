package com.itmo.services;

import com.itmo.dao.AmmunitionDao;
import com.pip.entities.Ammunition;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AmmunitionService {

    AmmunitionDao ammunitionDao;

    public List<Ammunition> findAllAmmunitions() {
        return ammunitionDao.findAllEntities();
    }

    public void addAmmunition(Ammunition ammunition) {
        ammunitionDao.saveEntity(ammunition);
    }

    public void deleteAmmunition(Ammunition ammunition) {
        ammunitionDao.removeEntity(ammunition);
    }

    public int deleteAmmunitionById(int ammunitionId) {
        return ammunitionDao.removeAmmunitionById(ammunitionId);
    }

    public Ammunition getAmmunitionById(int ammunitionId) {
        return ammunitionDao.findEntityById(ammunitionId);
    }

    public Ammunition updateAmmunition(Ammunition ammunition) {
        return ammunitionDao.updateEntity(ammunition);
    }
}
