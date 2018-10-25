package dao;

import entities.FirmEngine;

import javax.ejb.Stateless;

@Stateless
public class FirmEngineDao extends CommonDao<FirmEngine> {
    public FirmEngineDao() {
        super(FirmEngine.class);
    }
}
