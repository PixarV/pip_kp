package com.itmo.contollers;

import com.itmo.services.WeaponService;
import com.pip.entities.Ammunition;
import com.pip.entities.Weapon;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@AllArgsConstructor
@RequestMapping("/weapons")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class WeaponController {

    WeaponService weaponService;

    @GetMapping("/getAll")
    List<Weapon> findAllWeapons() {
        return weaponService.findAllWeapons();
    }

    @PostMapping("/add")
    Weapon addWeapon(@RequestBody Weapon weapon) {
        weaponService.addWeapon(weapon);
        return weapon;
    }

    @GetMapping("/get/{weaponId}")
    Weapon getWeapon(@PathVariable int weaponId) {
        return weaponService.getWeaponById(weaponId);
    }

    @PutMapping("/update")
    void updateWeapon(@RequestBody Weapon weapon) {
        weaponService.updateWeapon(weapon);
    }

    @DeleteMapping("/delete")
    void deleteWeapon(@RequestBody Weapon weapon) {
        weaponService.deleteWeapon(weapon);
    }

    @DeleteMapping("/delete/{weaponId}")
    void deleteWeaponById(@PathVariable int weaponId) {
        weaponService.deleteWeaponById(weaponId);
    }

    @PutMapping("/removeAmmunitionWeapon/{weaponId}")
    void removeAmmunitionFromMtoM(@PathVariable int weaponId) {
        weaponService.removeModelFromMtoM(weaponId);
    }

    @GetMapping("/getAmmunition/{weaponId}")
    List<Ammunition> getAmmunition(@PathVariable int weaponId) {
        return weaponService.getAmmunition(weaponId);
    }
}
