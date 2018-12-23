package com.itmo.dao;

import com.pip.entities.Human;
import org.springframework.stereotype.Repository;

@Repository
public class HumanDao extends CommonDao<Human> {
    public HumanDao() {
        super(Human.class);
    }
}
