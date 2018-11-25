package com.pip.services;

import com.pip.dao.FirmDao;
import com.pip.entities.Firm;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

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
        firmDao.saveEntity(firm);
    }
}
