package dao;

import entities.Chassis;

import javax.ejb.Stateless;

@Stateless
public class ChassisDao extends CommonDao<Chassis> {
    public ChassisDao() {
        super(Chassis.class);
    }
}
