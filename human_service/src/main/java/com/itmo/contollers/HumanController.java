package com.itmo.contollers;

import com.itmo.services.FirmService;
import com.pip.entities.Engine;
import com.pip.entities.Firm;
import com.pip.entities.Tower;
import com.pip.entities.Weapon;
import com.pip.enums.Approve;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@AllArgsConstructor
@RequestMapping("/firms")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class FirmController {

    FirmService firmService;

    @GetMapping("/getAll")
    List<Firm> getAllFirms() {
        return firmService.getAllFirms();
    }

    @PostMapping("/reg")
    Firm registrateFirm(@RequestBody Firm firm) {
        firmService.addFirm(firm);
        return firm;
    }

    @GetMapping("/get/{firmId}")
    Firm getFirm(@PathVariable int firmId) {
        return firmService.getFirmById(firmId);
    }

    @PutMapping("/update")
    void updateFirm(@RequestBody Firm firm) {
        firmService.updateFirm(firm);
    }

    @DeleteMapping("/delete")
    void deleteFirm(@RequestBody Firm firm) {
        firmService.deleteFirm(firm);
    }

    @DeleteMapping("/delete/{firmId}")
    void deleteFirmById(@PathVariable int firmId) {
        firmService.deleteFirmById(firmId);
    }

    @PutMapping("/changeStatus/{firmId}")
    void changeStatusOfFirm(@PathVariable int firmId, @RequestParam Approve status) {
        firmService.changeStatus(firmId, status);
    }

    @DeleteMapping("/removeFirmEngine/{firmId}/{engineId}")
    void removeFirmEngineFromMtoM(@PathVariable int firmId, @PathVariable int engineId) {
        firmService.removeFirmEngineFromMtoM(firmId, engineId);
    }

    @DeleteMapping("/removeFirmEngine/{firmId}")
    void removeFirmFromFtoE(@PathVariable int firmId) {
        firmService.removeFirmFromFtoE(firmId);
    }

    @DeleteMapping("/removeFirmTower/{firmId}/{towerId}")
    void removeFirmTowerFromMtoM(@PathVariable int firmId, @PathVariable int towerId) {
        firmService.removeFirmTowerFromMtoM(firmId, towerId);
    }

    @DeleteMapping("/removeFirmTower/{firmId}")
    void removeFirmFromFtoT(@PathVariable int firmId) {
        firmService.removeFirmFromFtoT(firmId);
    }

    @DeleteMapping("/removeFirmWeapon/{firmId}/{weaponId}")
    void removeFirmWeaponFromMtoM(@PathVariable int firmId, @PathVariable int weaponId) {
        firmService.removeFirmWeaponFromMtoM(firmId, weaponId);
    }

    @DeleteMapping("/removeFirmWeapon/{firmId}")
    void removeFirmFromFtoW(@PathVariable int firmId) {
        firmService.removeFirmFromFtoW(firmId);
    }

    @GetMapping("/getAllEngines/{firmId}")
    List<Engine> getAllEngines(@PathVariable int firmId) {
        return firmService.getAllEngines();
    }

    @GetMapping("/getAllTowers/{firmId}")
    List<Tower> getAllTowers(@PathVariable int firmId) {
        return firmService.getAllTowers();
    }

    @GetMapping("/getAllWeapons/{firmId}")
    List<Weapon> getAllWeapons(@PathVariable int firmId) {
        return firmService.getAllWeapons();
    }
}