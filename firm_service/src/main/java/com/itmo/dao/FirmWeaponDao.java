package com.itmo.dao;

import com.pip.entities.FirmWeapon;
import org.springframework.stereotype.Repository;

@Repository
public class FirmWeaponDao extends CommonDao<FirmWeapon> {
    public FirmWeaponDao() {
        super(FirmWeapon.class);
    }
}
