package com.itmo.dao;

import com.pip.entities.Human;
import com.pip.enums.Approve;
import com.pip.enums.UserRole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository
@Transactional
public class HumanDao extends CommonDao<Human> {
    public HumanDao() {
        super(Human.class);
    }

    public int custom_update(Human human) {
        Query query = entityManager.createQuery("update Human c set c.tank = :tank, " +
                "c.name = :name, " +
                "c.email = :email, " +
                "c.password = :password, " +
                "c.role = :role, " +
                "c.vacationStart = :vacationStart, " +
                "c.vacationEnd = :vacationEnd " +
                "WHERE c.id = :id");
        return query.setParameter("tank", human.getTank())
                .setParameter("name", human.getName())
                .setParameter("email", human.getEmail())
                .setParameter("password", human.getPassword())
                .setParameter("role", human.getRole())
                .setParameter("vacationStart", human.getVacationStart())
                .setParameter("vacationEnd", human.getVacationEnd())
                .setParameter("id", human.getId()).executeUpdate();
    }

    public int removeHumanById(int id) {
        Query query = entityManager.createQuery("DELETE FROM Human m WHERE m.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }

    public int changeStatus(int humanId, Approve status) {
        Query query = entityManager.createQuery("update Human c set c.status = :status WHERE c.id = :id");
        return query.setParameter("status", status)
                .setParameter("id", humanId)
                .executeUpdate();
    }

    public Human getHumanByEmail(String email) {
        Query query = entityManager.createQuery("FROM Human m WHERE m.email = :email");
        return (Human) query.setParameter("email", email).getSingleResult();
    }

    public int changeRole(int humanId, UserRole role) {
        Query query = entityManager.createQuery("update Human c set c.role = :role WHERE c.id = :id");
        return query.setParameter("role", role)
                .setParameter("id", humanId)
                .executeUpdate();
    }
}