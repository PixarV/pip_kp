package com.itmo.services;

import com.pip.enums.Approve;
import com.pip.entities.Firm;
import com.itmo.dao.FirmDao;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.pip.enums.Approve.APPROVED;
import static com.pip.enums.Approve.NOT_APPROVED;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class FirmService {

    FirmDao firmDao;

    public List<Firm> findAllFirms() {
        return firmDao.findAllEntities();
    }

    public void addFirm(Firm firm) {
        if (Objects.isNull(firm.getStatus()) || firm.getStatus() == APPROVED)
            firm.setStatus(NOT_APPROVED);

        firmDao.saveEntity(firm);
    }

    public void deleteFirm(Firm firm) {
        firmDao.removeEntity(firm);
    }

    public int deleteFirmById(int firmId) {
        return firmDao.removeFirmById(firmId);
    }

    public Firm getFirmById(int firmId) {
        return firmDao.findEntityById(firmId);
    }

    public void updateFirm(Firm firm) {
        firmDao.custom_update(firm);
    }

    public void changeStatus(int firmId, Approve status) {
        firmDao.changeStatus(firmId, status);
    }
}
