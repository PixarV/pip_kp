package com.itmo.dao;

import com.pip.entities.Model;
import org.springframework.stereotype.Repository;

@Repository
public class ModelDao extends CommonDao<Model> {
    public ModelDao() {
        super(Model.class);
    }
}

