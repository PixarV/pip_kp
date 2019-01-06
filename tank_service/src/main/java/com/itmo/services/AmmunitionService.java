package com.itmo.services;

import com.itmo.dao.AmmunitionDao;
import com.pip.entities.Ammunition;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
                    .addMessage("addForm:addButton", // id ratget form and target element
                            new FacesMessage("Error :(", e.getMessage()));
        }
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
