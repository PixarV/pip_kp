package dao;

import entities.Ammunition;

import javax.ejb.Stateless;

@Stateless
public class AmmunitionDao extends CommonDao<Ammunition> {
    public AmmunitionDao() {
        super(Ammunition.class);
    }
}

