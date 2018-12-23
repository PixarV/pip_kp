package com.itmo.dao;

import com.pip.entities.Tower;
import org.springframework.stereotype.Repository;

@Repository
public class TowerDao extends CommonDao<Tower> {
    public TowerDao() {
        super(Tower.class);
    }
}
