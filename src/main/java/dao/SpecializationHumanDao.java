package dao;

import entities.SpecializationHuman;

import javax.ejb.Stateless;

@Stateless
public class SpecializationHumanDao extends CommonDao<SpecializationHuman> {
    public SpecializationHumanDao() {
        super(SpecializationHuman.class);
    }
}
