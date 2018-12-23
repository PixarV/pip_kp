package com.itmo.dao;

import com.pip.entities.Engine;
import org.springframework.stereotype.Repository;

@Repository
public class EngineDao extends CommonDao<Engine> {
    public EngineDao() {
        super(Engine.class);
    }
}
