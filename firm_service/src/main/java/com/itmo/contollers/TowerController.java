package com.itmo.contollers;

import com.itmo.services.TowerService;
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

    @PostMapping("/add")
    Tower addTower(@RequestBody Tower tower) {
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
    void removeTowerFromMtoM(@PathVariable int towerId) {
        towerService.removeModelFromMtoM(towerId);
    }
}
