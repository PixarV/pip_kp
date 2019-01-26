package com.itmo.services;

import com.itmo.dao.FirmDao;
import com.itmo.dao.FirmEngineDao;
import com.itmo.dao.FirmTowerDao;
import com.itmo.dao.FirmWeaponDao;
import com.pip.entities.*;
import com.pip.enums.Approve;
import com.pip.enums.FirmSpecializtion;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.pip.enums.Approve.APPROVED;
import static com.pip.enums.Approve.NOT_APPROVED;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class FirmService implements UserDetailsService {

    FirmDao firmDao;
    FirmEngineDao firmEngineDao;
    FirmTowerDao firmTowerDao;
    FirmWeaponDao firmWeaponDao;
    JavaMailSender emailSender;

    public List<Firm> getAllFirms() {
        return firmDao.findAllEntities();
    }

    public List<Firm> getFirm() {
        Firm firm = (Firm) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Collections.singletonList(firmDao.findEntityById(firm.getId()));
    }

    public void addFirm(Firm firm) {
        if (Objects.isNull(firm.getStatus()) || firm.getStatus() == APPROVED)
            firm.setStatus(NOT_APPROVED);

        sendSimpleMessage(firm.getEmail(), "Registration", "you are registered");
        Firm tempFirm = firm.withId(0);
        firmDao.saveEntity(tempFirm);
        firm.setTitle("");
        firm.setEmail("");
        firm.setSpecialization(null);
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void deleteFirm(Firm firm) {
        firmDao.removeEntity(firm);
    }

    public int deleteFirmById(int firmId) {
        return firmDao.removeFirmById(firmId);
    }

    public Firm getFirmById(int firmId) {
        return firmDao.findEntityById(firmId);
    }

    public void updateFirm(Firm tempFirm) {
        Firm firm = (Firm) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!tempFirm.getTitle().equals("")) {
            firm.setTitle(tempFirm.getTitle());
        }
        if (!tempFirm.getEmail().equals("")) {
            firm.setEmail(tempFirm.getEmail());
        }
        if (!tempFirm.getPassword().equals("")) {
            firm.setPassword(tempFirm.getPassword());
        }
        firmDao.custom_update(firm);
        tempFirm.setEmail("");
        tempFirm.setTitle("");
    }

    public void approveFirm(int firmId) {
        changeStatus(firmId, APPROVED);
    }

    public void changeStatus(int firmId, Approve status) {
        firmDao.changeStatus(firmId, status);
    }

    public void changeStatus(int firmId) {
        Firm firm = firmDao.findEntityById(firmId);
        Approve status = (firm.getStatus() == APPROVED) ? NOT_APPROVED : APPROVED;
        firmDao.changeStatus(firmId, status);
    }

    public void removeFirmEngineFromMtoM(int firmId, int engineId) {
        firmEngineDao.removeFirmEngine(firmId, engineId);
    }

    public void removeFirmFromFtoE(int firmId) {
        firmEngineDao.removeFirmFromFtoE(firmId);
    }

    public void removeFirmTowerFromMtoM(int firmId, int towerId) {
        firmTowerDao.removeFirmTower(firmId, towerId);
    }

    public void removeFirmFromFtoT(int firmId) {
        firmTowerDao.removeFirmFromFtoT(firmId);
    }

    public void removeFirmWeaponFromMtoM(int firmId, int weaponId) {
        firmWeaponDao.removeFirmWeapon(firmId, weaponId);
    }

    public void removeFirmFromFtoW(int firmId) {
        firmWeaponDao.removeFirmFromFtoW(firmId);
    }

    public List<Engine> getAllEngines() {
        Firm firm = (Firm) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return firmEngineDao.getAllEngines(firm);
    }

    public List<Tower> getAllTowers() {
        Firm firm = (Firm) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return firmTowerDao.getAllTowers(firm);
    }

    public List<Weapon> getAllWeapons() {
        Firm firm = (Firm) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return firmWeaponDao.getAllWeapons(firm);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("hey" + username);
        return firmDao.getFirmByEmail(username);
    }

    public boolean isEng() {
        Firm firm = (Firm) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FirmSpecializtion spec = firm.getSpecialization();
        return spec == FirmSpecializtion.ENGINE || spec == FirmSpecializtion.BOTH;
    }

    public boolean isTank() {
        Firm firm = (Firm) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FirmSpecializtion spec = firm.getSpecialization();
        return spec == FirmSpecializtion.TANK || spec == FirmSpecializtion.BOTH;
    }
}

