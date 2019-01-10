package com.itmo.dao;

import com.pip.enums.Approve;
import com.pip.entities.Firm;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository
@Transactional
public class FirmDao extends CommonDao<Firm> {
    public FirmDao() {
        super(Firm.class);
    }

    public int custom_update(Firm firm) {
        Query query = entityManager.createQuery("update Firm c set c.specialization = :specialization, " +
                "c.title = :title, " +
                "c.email = :email " +
                "WHERE c.id = :id");
        return query.setParameter("specialization", firm.getSpecialization())
                .setParameter("title", firm.getTitle())
                .setParameter("email", firm.getEmail())
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
}