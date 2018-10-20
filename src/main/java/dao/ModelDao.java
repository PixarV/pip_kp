package dao;

import entities.Model;

import javax.ejb.Stateless;

@Stateless
public class ModelDao extends CommonDao<Model> {
    public ModelDao() {
        super(Model.class);
    }
}

