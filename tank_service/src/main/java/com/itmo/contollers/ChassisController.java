package com.itmo.contollers;

import com.itmo.services.ChassisService;
import com.pip.entities.Chassis;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Controller
@AllArgsConstructor
@RequestMapping("/chassiss")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ChassisController {

    ChassisService chassisService;

//    @ResponseBody
//    @GetMapping("/getAll")
//    List<Chassis> findAllChassiss() {
//        return chassisService.findAllChassiss();
//    }

    @ResponseBody
    @PostMapping("/add")
    Chassis addChassis(@RequestBody Chassis chassis) {
        chassisService.addChassis(chassis);
        return chassis;
    }

//    @ResponseBody
//    @GetMapping("/get/{chassisId}")
//    Chassis getChassis(@PathVariable int chassisId) {
//        return chassisService.getChassisById(chassisId);
//    }

    @ResponseBody
    @PostMapping("/update")
    Chassis updateChassis(@RequestBody Chassis chassis) {
        return chassisService.updateChassis(chassis);
    }

    @ResponseBody
    @DeleteMapping("/delete")
    void deleteChassis(@RequestBody Chassis chassis) {
        chassisService.deleteChassis(chassis);
    }

    @ResponseBody
    @DeleteMapping("/delete/{chassisId}")
    void deleteChassisById(@PathVariable int chassisId) {
        chassisService.deleteChassisById(chassisId);
    }


}
