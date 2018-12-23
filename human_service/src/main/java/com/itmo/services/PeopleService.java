package com.itmo.services;

import com.itmo.dao.HumanDao;
import com.pip.entities.Human;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PeopleService {

    HumanDao humanDao;

    public List<Human> findAllHumans() {
        return humanDao.findAllEntities();
    }

    public void addHuman(Human human) {
        humanDao.saveEntity(human);
    }
}
