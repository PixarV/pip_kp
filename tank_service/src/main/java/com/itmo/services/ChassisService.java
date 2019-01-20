package com.itmo.services;

import com.itmo.dao.ChassisDao;
import com.itmo.dao.ModelDao;
import com.pip.entities.Chassis;
import com.pip.entities.Model;
import com.pip.entities.Tower;
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
public class ChassisService {

    ChassisDao chassisDao;
    ModelDao modelDao;

    public List<Chassis> findAllChassiss() {
        return chassisDao.findAllEntities();
    }

    public void addChassis(Chassis chassis) {
        try {
            Chassis tempChass = chassis.withId(0);
            Model model = modelDao.findEntityById(tempChass.getModel().getId());
            tempChass.setModel(model);
            chassisDao.saveEntity(tempChass);
        } catch (Exception e) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage("addChasForm:addChasButton", // id ratget form and target element
                            new FacesMessage("Error :(", e.getMessage()));
        }
    }

    public void deleteChassis(Chassis chassis) {
        chassisDao.removeEntity(chassis);
    }

    public int deleteChassisById(int chassisId) {
        return chassisDao.removeChassisById(chassisId);
    }

    public List<Chassis> getChassisById(int chassisId) {
        if (chassisId != 0) {
            Chassis tempChassis = chassisDao.findEntityById(chassisId);
            if (tempChassis != null)
                return Arrays.asList(tempChassis);
            else {
                FacesContext
                        .getCurrentInstance()
                        .addMessage("shortChasGetForm:shortChasGetButton", // id ratget form and target element
                                new FacesMessage("Error :(", "Can't find entry"));
            }
        }
        return new ArrayList<>();
    }

    public void updateChassis(Chassis chassis) {
        chassisDao.custom_update(chassis);
        chassisDao.removeChassisFromMtoM(chassis.getId());
    }

    public void addTower(Chassis chassis, int towerId) {
        chassisDao.addTower(chassis.getId(), towerId);
    }

    public void removeChassisFromMtoM(int chassisId) {
        chassisDao.removeChassisFromMtoM(chassisId);
    }

    public List<Tower> getTowers(int chassisId) {
        return chassisDao.getTowers(chassisId);
    }
}
