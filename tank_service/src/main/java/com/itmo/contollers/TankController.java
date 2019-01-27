package com.itmo.contollers;

import com.itmo.services.TankService;
import com.pip.entities.Tank;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@AllArgsConstructor
@RequestMapping("/tanks")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TankController {

    TankService tankService;

    @GetMapping("/getAll")
    List<Tank> findAllTanks() {
        return tankService.getAllTanks();
    }

    @PostMapping("/add")
    Tank addTank(@RequestBody Tank tank) {
        tankService.addTank(tank);
        return tank;
    }

    @GetMapping("/get/{tankId}")
    Tank getTank(@PathVariable int tankId) {
        return tankService.getTankById(tankId).get(0);
    }

    @PutMapping("/update")
    void updateTank(@RequestBody Tank tank) {
        tankService.updateTank(tank);
    }

    @DeleteMapping("/delete")
    void deleteTank(@RequestBody Tank tank) {
        tankService.deleteTank(tank);
    }

    @DeleteMapping("/delete/{tankId}")
    void deleteTankById(@PathVariable int tankId) {
        tankService.deleteTankById(tankId);
    }
}
