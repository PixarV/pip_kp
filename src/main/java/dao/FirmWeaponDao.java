package dao;

import entities.FirmWeapon;

import javax.ejb.Stateless;

@Stateless
public class FirmWeaponDao extends CommonDao<FirmWeapon> {
    public FirmWeaponDao() {
        super(FirmWeapon.class);
    }
}
