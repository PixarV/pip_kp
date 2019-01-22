package com.itmo.contollers;

import com.itmo.services.HumanService;
import com.pip.entities.Human;
import com.pip.enums.Approve;
import com.pip.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@AllArgsConstructor
@RequestMapping("/humans")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class HumanController {

    HumanService humanService;

    @GetMapping("/getAll")
    List<Human> getAllHumans() {
        return humanService.getAllHumans();
    }

    @PostMapping("/reg")
    Human registrateHuman(@RequestBody Human human) {
        humanService.addHuman(human);
        return human;
    }

    @GetMapping("/get/{humanId}")
    Human getHuman(@PathVariable int humanId) {
        return humanService.getHumanById(humanId);
    }

    @PutMapping("/update")
    void updateHuman(@RequestBody Human human) {
        humanService.updateHuman(human);
    }

    @DeleteMapping("/delete")
    void deleteHuman(@RequestBody Human human) {
        humanService.deleteHuman(human);
    }

    @DeleteMapping("/delete/{humanId}")
    void deleteHumanById(@PathVariable int humanId) {
        humanService.deleteHumanById(humanId);
    }

    @PutMapping("/changeStatus")
    void changeStatusOfHuman(@RequestParam Approve status) {
        humanService.changeStatus(status);
    }

    @PutMapping("/changeRole")
    void changeRoleOfHuman(@RequestParam UserRole role) {
        humanService.changeRole(role);
    }
}