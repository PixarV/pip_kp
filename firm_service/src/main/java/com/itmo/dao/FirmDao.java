package com.itmo.dao;

import com.pip.entities.*;
import com.pip.enums.Approve;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class FirmDao extends CommonDao<Firm> {
    public FirmDao() {
        super(Firm.class);
    }

    public int custom_update(Firm firm) {
        Query query = entityManager.createQuery("update Firm c set c.specialization = :specialization, " +
                "c.title = :title, " +
                "c.email = :email, " +
                "c.password = :password " +
                "WHERE c.id = :id");
        return query.setParameter("specialization", firm.getSpecialization())
                .setParameter("title", firm.getTitle())
                .setParameter("email", firm.getEmail())
                .setParameter("password", firm.getPassword())
                .setParameter("id", firm.getId()).executeUpdate();
    }

    public int removeFirmById(int id) {
        Query query = entityManager.createQuery("DELETE FROM Firm m WHERE m.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }

    public int changeStatus(int firmId, Approve status) {
        Query query = entityManager.createQuery("update Firm c set c.status = :status WHERE c.id = :id");
        return query.setParameter("status", status)
                .setParameter("id", firmId)
                .executeUpdate();
    }

    public Firm getFirmByEmail(String email) {
        Query query = entityManager.createQuery("FROM Firm m WHERE m.email = :email");
        return (Firm) query.setParameter("email", email).getSingleResult();
    }

    public List<Monster> getAllFirmEngine() {
        Query query = entityManager.createQuery("FROM FirmEngine f");
        List<FirmEngine> resultList = query.getResultList();

        List<Monster> result = new ArrayList<>();
        for (FirmEngine elem : resultList) {
            Monster monster = Monster.builder()
                    .firmTitle(elem.getFirm().getTitle())
                    .engineTitle(elem.getEngine().getTitle())
                    .engineSn(elem.getSerialNo())
                    .build();
            result.add(monster);
        }
        return result;
    }

    public List<Monster> getAllFirmTower() {
        Query query = entityManager.createQuery("FROM FirmTower f");
        List<FirmTower> resultList = query.getResultList();

        List<Monster> result = new ArrayList<>();
        for (FirmTower elem : resultList) {
            Monster monster = Monster.builder()
                    .firmTitle(elem.getFirm().getTitle())
                    .towerTitle(elem.getTower().getTitle())
                    .towerSn(elem.getSerialNo())
                    .build();
            result.add(monster);
        }
        return result;
    }

    public List<Monster> getAllFirmWeapon() {
        Query query = entityManager.createQuery("FROM FirmWeapon f");
        List<FirmWeapon> resultList = query.getResultList();

        List<Monster> result = new ArrayList<>();
        for (FirmWeapon elem : resultList) {
            Monster monster = Monster.builder()
                    .firmTitle(elem.getFirm().getTitle())
                    .weaponTitle(elem.getWeapon().getTitle())
                    .weaponSn(elem.getSerialNo())
                    .build();
            result.add(monster);
        }
        return result;
    }
}