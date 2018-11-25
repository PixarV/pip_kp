package com.pip.dao;

import com.pip.entities.FirmTower;
import org.springframework.stereotype.Repository;

@Repository
public class FirmTowerDao extends CommonDao<FirmTower> {
    public FirmTowerDao() {
        super(FirmTower.class);
    }
}
