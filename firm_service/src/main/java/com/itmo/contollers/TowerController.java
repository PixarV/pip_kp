package com.itmo.contollers;

import com.itmo.services.TowerService;
import com.pip.entities.Chassis;
import com.pip.entities.Firm;
import com.pip.entities.Tower;
import com.pip.entities.Weapon;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@AllArgsConstructor
@RequestMapping("/towers")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TowerController {

    TowerService towerService;

    @GetMapping("/getAll")
    List<Tower> findAllTowers() {
        return towerService.findAllTowers();
    }

    @PostMapping("/add/{firmId}")
    Tower addTower(@RequestBody Tower tower, @PathVariable int firmId) {
        towerService.addTower(tower);
        return tower;
    }

    @GetMapping("/get/{towerId}")
    Tower getTower(@PathVariable int towerId) {
        return towerService.getTowerById(towerId);
    }

    @PutMapping("/update")
    void updateTower(@RequestBody Tower tower) {
        towerService.updateTower(tower);
    }

    @DeleteMapping("/delete")
    void deleteTower(@RequestBody Tower tower) {
        towerService.deleteTower(tower);
    }

    @DeleteMapping("/delete/{towerId}")
    void deleteTowerById(@PathVariable int towerId) {
        towerService.deleteTowerById(towerId);
    }

    @PutMapping("/removeChassisTower/{towerId}")
    void removeTowerFromMtoMCT(@PathVariable int towerId) {
        towerService.removeTowerFromMtoMCT(towerId);
    }

    @GetMapping("/getChassis/{towerId}")
    List<Chassis> getChassis(@PathVariable int towerId) {
        return towerService.getChassis(towerId);
    }

    @GetMapping("/getTowerSn/{firmId}/{towerId}")
    String getTowerSn(@PathVariable int firmId, @PathVariable int towerId) {
        return towerService.getTowerSn(firmId, towerId);
    }

    @PutMapping("/addWeapon/{weaponId}")
    void addWeaponToTower(@RequestBody Tower tower,
                          @PathVariable int weaponId) {
        towerService.addWeapon(tower, weaponId);
    }

    @PutMapping("/removeTowerWeapon/{towerId}")
    void removeTowerFromMtoM(@PathVariable int towerId) {
        towerService.removeTowerFromMtoMTW(towerId);
    }

    @GetMapping("/getWeapons/{towerId}")
    List<Weapon> getWeapons(@PathVariable int towerId) {
        return towerService.getWeapons(towerId);
    }

    @GetMapping("/getFirms/{towerId}")
    List<Firm> getFirms(@PathVariable int towerId) {
        return towerService.getFirms(towerId);
    }
}
