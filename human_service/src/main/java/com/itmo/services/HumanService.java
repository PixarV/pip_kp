package com.itmo.services;

import com.itmo.dao.HumanDao;
import com.pip.entities.Human;
import com.pip.entities.Tank;
import com.pip.enums.Approve;
import com.pip.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.pip.enums.Approve.APPROVED;
import static com.pip.enums.Approve.NOT_APPROVED;
import static com.pip.enums.UserRole.MODERATOR;
import static com.pip.enums.UserRole.USER;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class HumanService implements UserDetailsService {

    HumanDao humanDao;
    JavaMailSender emailSender;

    public List<Human> getAllHumans() {
        return humanDao.findAllEntities();
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void addHuman(Human human) {
        if (Objects.isNull(human.getStatus()) || human.getStatus() == APPROVED)
            human.setStatus(NOT_APPROVED);

        if (Objects.isNull(human.getRole()) || human.getRole() == MODERATOR)
            human.setRole(USER);

        sendSimpleMessage(human.getEmail(), "Registration", "you are registered");
        Human tempHuman = human.withId(0);
        human.setName("");
        human.setEmail("");
        humanDao.saveEntity(tempHuman);
    }

    public void deleteHuman(Human human) {
        humanDao.removeEntity(human);
    }

    public int deleteHumanById(int humanId) {
        return humanDao.removeHumanById(humanId);
    }

    public List<Human> getHuman() {
        Human human = (Human) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Collections.singletonList(humanDao.findEntityById(human.getId()));
    }

    public void updateHuman(Human human) {
        humanDao.custom_update(human);
    }

    public void approveHuman(int humanId) {
        changeStatus(APPROVED);
    }

    public void changeStatus(Approve status) {
        Human human = (Human) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        humanDao.changeStatus(human.getId(), status);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("hey" + username);
        return humanDao.getHumanByEmail(username);
    }

    public void changeRole(UserRole role) {
        Human human = (Human) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        humanDao.changeRole(human.getId(), role);
    }

    public boolean isModerator() {
        Human human = (Human) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return human.getRole() == UserRole.MODERATOR;
    }

    public void customAddHuman(Human human, int tankId) {
        Tank tank = new Tank();
        tank.setId(tankId);
        human.setTank(tank);
        humanDao.saveEntity(human);
    }

    public Human getHumanById(int humanId) {
        return humanDao.findEntityById(humanId);
    }
}
