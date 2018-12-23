package com.itmo.dao;

import com.pip.entities.Chassis;
import org.springframework.stereotype.Repository;

@Repository
public class ChassisDao extends CommonDao<Chassis> {
    public ChassisDao() {
        super(Chassis.class);
    }
}
