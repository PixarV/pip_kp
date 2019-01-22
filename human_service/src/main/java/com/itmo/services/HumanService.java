package com.itmo.services;

import com.itmo.dao.HumanDao;
import com.pip.entities.Human;
import com.pip.enums.Approve;
import com.pip.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.pip.enums.Approve.APPROVED;
import static com.pip.enums.Approve.NOT_APPROVED;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class HumanService implements UserDetailsService {

    HumanDao humanDao;

    public List<Human> getAllHumans() {
        return humanDao.findAllEntities();
    }

    public void addHuman(Human human) {
        if (Objects.isNull(human.getStatus()) || human.getStatus() == APPROVED)
            human.setStatus(NOT_APPROVED);

        humanDao.saveEntity(human);
    }

    public void deleteHuman(Human human) {
        humanDao.removeEntity(human);
    }

    public int deleteHumanById(int humanId) {
        return humanDao.removeHumanById(humanId);
    }

    public Human getHumanById(int humanId) {
        return humanDao.findEntityById(humanId);
    }

    public void updateHuman(Human human) {
        humanDao.custom_update(human);
    }

    public void approveHuman(int humanId) {
        changeStatus(humanId, APPROVED);
    }

    public void changeStatus(int humanId, Approve status) {
        humanDao.changeStatus(humanId, status);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("hey" + username);
        return humanDao.getHumanByEmail(username);
    }

    public void changeRole(int humanId, UserRole role) {
        humanDao.changeRole(humanId, role);
    }
}
