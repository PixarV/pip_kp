package com.pip.dao;

import com.pip.entities.SpecializationHuman;
import org.springframework.stereotype.Repository;

@Repository
public class SpecializationHumanDao extends CommonDao<SpecializationHuman> {
    public SpecializationHumanDao() {
        super(SpecializationHuman.class);
    }
}
