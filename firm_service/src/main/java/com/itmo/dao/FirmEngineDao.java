package com.itmo.dao;

import com.pip.entities.FirmEngine;
import org.springframework.stereotype.Repository;

@Repository
public class FirmEngineDao extends CommonDao<FirmEngine> {
    public FirmEngineDao() {
        super(FirmEngine.class);
    }
}
