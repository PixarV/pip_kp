package dao;

import entity.Engine;

import javax.ejb.Stateless;

@Stateless
public class EngineDao extends CommonDao<Engine> {
    public EngineDao() {
        super(Engine.class);
    }
}
