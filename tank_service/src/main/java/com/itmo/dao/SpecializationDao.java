package com.itmo.dao;

import com.pip.entities.Specialization;
import org.springframework.stereotype.Repository;

@Repository
public class SpecializationDao extends CommonDao<Specialization> {
    public SpecializationDao() {
        super(Specialization.class);
    }
}
