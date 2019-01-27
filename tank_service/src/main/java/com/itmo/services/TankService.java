package com.itmo.services;

import com.itmo.dao.TankDao;
import com.pip.entities.Tank;
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
public class TankService {

    TankDao tankDao;

    public List<Tank> getAllTanks() {
        return tankDao.findAllEntities();
    }

    public void addTank(Tank tank) {
        try {
            Tank tempTank = tank.withId(0);
            tankDao.saveEntity(tempTank);
        } catch (Exception e) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage("addTankForm:addTankButton", // id ratget form and target element
                            new FacesMessage("Error :(", e.getMessage()));
        }
    }

    public void deleteTank(Tank tank) {
        tankDao.removeEntity(tank);
    }

    public int deleteTankById(int tankId) {
        return tankDao.removeTankById(tankId);
    }

    public List<Tank> getTankById(int tankId) {
        if (tankId != 0) {
            Tank tempTank = tankDao.findEntityById(tankId);
            if (tempTank != null)
                return Arrays.asList(tempTank);
            else {
                FacesContext
                        .getCurrentInstance()
                        .addMessage("shortTankGetForm:shortTankGetButton", // id ratget form and target element
                                new FacesMessage("Error :(", "Can't find entry"));
            }
        }
        return new ArrayList<>();
    }

    public void updateTank(Tank tank) {
        tankDao.custom_update(tank);
    }
}
