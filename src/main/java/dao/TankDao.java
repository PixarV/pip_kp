package dao;

import entities.Tank;

import javax.ejb.Stateless;

@Stateless
public class TankDao extends CommonDao<Tank> {
    public TankDao() {
        super(Tank.class);
    }
}

