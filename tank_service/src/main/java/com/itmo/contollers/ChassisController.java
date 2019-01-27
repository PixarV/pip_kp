package com.itmo.contollers;

import com.itmo.services.ChassisService;
import com.pip.entities.Chassis;
import com.pip.entities.Tower;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@AllArgsConstructor
@RequestMapping("/chassiss")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ChassisController {

    ChassisService chassisService;

    @GetMapping("/getAll")
    List<Chassis> findAllChassiss() {
        return chassisService.findAllChassiss();
    }

    @PostMapping("/add")
    Chassis addChassis(@RequestBody Chassis chassis) {
        chassisService.addChassis(chassis, chassis.getModel().getId());
        return chassis;
    }

    @GetMapping("/get/{chassisId}")
    Chassis getChassis(@PathVariable int chassisId) {
        return chassisService.getChassisById(chassisId).get(0);
    }

    @PutMapping("/update")
    void updateChassis(@RequestBody Chassis chassis) {
       chassisService.updateChassis(chassis, chassis.getModel().getId());
    }

    @DeleteMapping("/delete")
    void deleteChassis(@RequestBody Chassis chassis) {
        chassisService.deleteChassis(chassis, chassis.getModel().getId());
    }

    @DeleteMapping("/delete/{chassisId}")
    void deleteChassisById(@PathVariable int chassisId) {
        chassisService.deleteChassisById(chassisId);
    }

    @PutMapping("/addTower/{towerId}")
    void addTowerToChassis(@RequestBody Chassis chassis,
                           @PathVariable int towerId) {
        chassisService.addTower(chassis.getId(), towerId);
    }

    @PutMapping("/removeChassisTower/{chassisId}")
    void removeModelFromMtoM(@PathVariable int chassisId) {
        chassisService.removeChassisFromMtoM(chassisId);
    }

    @GetMapping("/getTowers/{chassisId}")
    List<Tower> getTowers(@PathVariable int chassisId) {
        return chassisService.getTowers(chassisId);
    }
}
