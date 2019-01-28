package com.itmo.services;

import com.itmo.dao.TankDao;
import com.pip.entities.Tank;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.HibernateException;
import org.postgresql.util.PSQLException;
import org.springframework.orm.jpa.JpaSystemException;
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
            tempTank.setSnEngine(tank.getSnEngine().trim());
            tempTank.setSnTower(tank.getSnTower().trim());
            tempTank.setSnWeapon(tank.getSnWeapon().trim());
            tankDao.saveEntity(tempTank);
            FacesContext
                    .getCurrentInstance()
                    .addMessage("addTankForm:addTankButton", // id ratget form and target element
                            new FacesMessage("Success", "Success"));
            tank.setSnWeapon("");
            tank.setSnEngine("");
            tank.setSnTower("");
            tank.setIdChassis(0);
            tank.setIdModel(0);
            tank.setTeamNumber(0);
        } catch (JpaSystemException e) {
            String errorMessage = e.getCause().getCause().getMessage();
            String outputError = errorMessage.substring(0, errorMessage.indexOf("Where"));
            FacesContext
                    .getCurrentInstance()
                    .addMessage("addTankForm:addTankButton", // id ratget form and target element
                            new FacesMessage("Error :(", outputError));
        } catch (Exception e) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage("addTankForm:addTankButton", // id ratget form and target element
                            new FacesMessage("на всякий :(", e.getMessage()));
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

    public void updateTank(Tank tempTank) {
        Tank tank = tankDao.findEntityById(tempTank.getId());

        if (!tempTank.getSnEngine().equals("")) {
            tank.setSnEngine(tempTank.getSnEngine());
        }

        if (!tempTank.getSnTower().equals("")) {
            tank.setSnTower(tempTank.getSnTower());
        }
        if (!tempTank.getSnWeapon().equals("")) {
            tank.setSnWeapon(tempTank.getSnWeapon());
        }

        if (tempTank.getIdChassis() != 0) {
            tank.setIdChassis(tempTank.getIdChassis());
        }
        if (tempTank.getIdModel() != 0) {
            tank.setIdModel(tempTank.getIdModel());
        }
        if (tempTank.getTeamNumber() != 0) {
            tank.setTeamNumber(tempTank.getTeamNumber());
        }
        tankDao.custom_update(tank);
        tempTank.setSnWeapon("");
        tempTank.setSnEngine("");
        tempTank.setSnTower("");
        tempTank.setIdChassis(0);
        tempTank.setIdModel(0);
        tempTank.setTeamNumber(0);
    }
}
