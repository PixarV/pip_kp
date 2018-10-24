package dao;

import entities.Weapon;

import javax.ejb.Stateless;

@Stateless
public class WeaponDao extends CommonDao<Weapon> {
    public WeaponDao() {
        super(Weapon.class);
    }
}

