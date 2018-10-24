package dao;

import entities.Tower;

import javax.ejb.Stateless;

@Stateless
public class TowerDao extends CommonDao<Tower> {
    public TowerDao() {
        super(Tower.class);
    }
}
