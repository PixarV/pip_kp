package com.itmo.services;

import com.itmo.dao.AmmunitionDao;
import com.pip.entities.Ammunition;
import com.pip.entities.Weapon;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Arrays;
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
        try {
            Ammunition tempAmmun = ammunition.withId(0);
            ammunitionDao.saveEntity(tempAmmun);
        } catch (Exception e) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage("addAmunForm:addAmunButton", // id ratget form and target element
                            new FacesMessage("Error :(", e.getMessage()));
        }
    }

    public void deleteAmmunition(Ammunition ammunition) {
        ammunitionDao.removeEntity(ammunition);
    }

    public int deleteAmmunitionById(int ammunitionId) {
        return ammunitionDao.removeAmmunitionById(ammunitionId);
    }

    public List<Ammunition> getAmmunitionById(int ammunitionId) {
        if (ammunitionId != 0) {
            Ammunition tempAmun = ammunitionDao.findEntityById(ammunitionId);
            if (tempAmun != null)
                return Arrays.asList(tempAmun);
            else {
                FacesContext
                        .getCurrentInstance()
                        .addMessage("shortAmunGetForm:shortAmunGetButton", // id ratget form and target element
                                new FacesMessage("Error :(", "Can't find entry"));
            }
        }
        return new ArrayList<>();
    }

    public void updateAmmunition(Ammunition tempAmmunition) {
        Ammunition ammunition = ammunitionDao.findEntityById(tempAmmunition.getId());

        if (tempAmmunition.getBreakage() != 0) {
            ammunition.setBreakage(tempAmmunition.getBreakage());
        }
        if (tempAmmunition.getCallibr() != 0) {
            ammunition.setCallibr(tempAmmunition.getCallibr());
        }

        ammunitionDao.custom_update(ammunition);
        ammunitionDao.removeAmmunitionFromMtoM(ammunition.getId());

        tempAmmunition.setBreakage(0);
        tempAmmunition.setCallibr(0);
    }

    public void addWeapon(int ammunId, int weaponId) {
        ammunitionDao.addWeapon(ammunId, weaponId);
    }

    public void removeAmmunitionFromMtoM(int ammunitionId) {
        ammunitionDao.removeAmmunitionFromMtoM(ammunitionId);
    }

    public List<Weapon> getWeapons(int ammunitionId) {
        return ammunitionDao.getWeapons(ammunitionId);
    }
}
