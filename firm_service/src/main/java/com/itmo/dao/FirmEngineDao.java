package com.itmo.dao;

import com.pip.entities.Engine;
import com.pip.entities.Firm;
import com.pip.entities.FirmEngine;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class FirmEngineDao extends CommonDao<FirmEngine> {
    public FirmEngineDao() {
        super(FirmEngine.class);
    }

    public int removeFirmEngine(int firmId, int engineId) {
        Query query = entityManager.createQuery("DELETE FROM FirmEngine m " +
                "WHERE m.id_firm = :firm AND m.id_engine = :engine");
        return query.setParameter("firm", firmId)
                .setParameter("engine", engineId)
                .executeUpdate();
    }

    public int removeFirmFromFtoE(int firmId) {
        Query query = entityManager.createQuery("DELETE FROM FirmEngine m " +
                "WHERE m.id_firm = :firm");
        return query.setParameter("firm", firmId)
                .executeUpdate();
    }

    public String getEngineSn(int firmId, int engineId) {
        Query query = entityManager.createNativeQuery("SELECT serial_no FROM firm_engine " +
                "WHERE id_firm = ? AND id_engine = ?");
        return (String) query.setParameter(1, firmId)
                .setParameter(2, engineId)
                .getSingleResult();
    }

    public List<Engine> getAllEngines(Firm firm) {
        Query query = entityManager.createQuery("select engine from FirmEngine t where t.firm=:firm");
        query.setParameter("firm", firm);
        return query.getResultList();
    }
}
