package com.itmo.contollers;

import com.itmo.services.TowerService;
import com.pip.entities.Chassis;
import com.pip.entities.Tower;
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
        towerService.addTower(tower, firmId);
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
    void removeTowerFromMtoM(@PathVariable int towerId) {
        towerService.removeModelFromMtoM(towerId);
    }

    @GetMapping("/getChassis/{towerId}")
    List<Chassis> getChassis(@PathVariable int towerId) {
        return towerService.getChassis(towerId);
    }

    @GetMapping("/getTowerSn/{firmId}/{towerId}")
    String getTowerSn(@PathVariable int firmId, @PathVariable int towerId) {
        return towerService.getTowerSn(firmId, towerId);
    }
}
