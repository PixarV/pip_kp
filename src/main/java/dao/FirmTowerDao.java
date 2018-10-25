package dao;

import entities.FirmTower;

import javax.ejb.Stateless;

@Stateless
public class FirmTowerDao extends CommonDao<FirmTower> {
    public FirmTowerDao() {
        super(FirmTower.class);
    }
}
