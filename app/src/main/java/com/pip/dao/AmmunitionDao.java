package com.pip.dao;

import com.pip.entities.Ammunition;
import org.springframework.stereotype.Repository;

@Repository
public class AmmunitionDao extends CommonDao<Ammunition> {
    public AmmunitionDao() {
        super(Ammunition.class);
    }
}

