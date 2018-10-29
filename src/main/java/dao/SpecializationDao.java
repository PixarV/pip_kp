package dao;

import entities.Specialization;

import javax.ejb.Stateless;

@Stateless
public class SpecializationDao extends CommonDao<Specialization> {
    public SpecializationDao() {
        super(Specialization.class);
    }
}
