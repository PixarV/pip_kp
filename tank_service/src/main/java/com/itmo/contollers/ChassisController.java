package com.itmo.contollers;

import com.itmo.services.ChassisService;
import com.pip.entities.Chassis;
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
        chassisService.addChassis(chassis);
        return chassis;
    }

    @GetMapping("/get/{chassisId}")
    Chassis getChassis(@PathVariable int chassisId) {
        return chassisService.getChassisById(chassisId);
    }

    @PutMapping("/update")
    void updateChassis(@RequestBody Chassis chassis) {
       chassisService.updateChassis(chassis);
    }

    @DeleteMapping("/delete")
    void deleteChassis(@RequestBody Chassis chassis) {
        chassisService.deleteChassis(chassis);
    }

    @DeleteMapping("/delete/{chassisId}")
    void deleteChassisById(@PathVariable int chassisId) {
        chassisService.deleteChassisById(chassisId);
    }


}
