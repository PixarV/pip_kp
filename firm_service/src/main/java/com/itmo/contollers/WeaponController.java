package com.itmo.contollers;

import com.itmo.services.WeaponService;
import com.pip.entities.Ammunition;
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
@RequestMapping("/weapons")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class WeaponController {

    WeaponService weaponService;

    @GetMapping("/getAll")
    List<Weapon> findAllWeapons() {
        return weaponService.findAllWeapons();
    }

    @PostMapping("/add/{firmId}")
    Weapon addWeapon(@RequestBody Weapon weapon, @PathVariable int firmId) {
        weaponService.addWeapon(weapon, firmId);
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
    void removeWeaponFromMtoMAW(@PathVariable int weaponId) {
        weaponService.removeWeaponFromMtoMAW(weaponId);
    }

    @GetMapping("/getAmmunition/{weaponId}")
    List<Ammunition> getAmmunition(@PathVariable int weaponId) {
        return weaponService.getAmmunition(weaponId);
    }

    @PutMapping("/removeTowerWeapon/{weaponId}")
    void removeWeaponFromMtoM(@PathVariable int weaponId) {
        weaponService.removeWeaponFromMtoMTW(weaponId);
    }

    @GetMapping("/getTowers/{weaponId}")
    List<Tower> getTowers(@PathVariable int weaponId) {
        return weaponService.getTowers(weaponId);
    }

    @GetMapping("/getWeaponSn/{firmId}/{weaponId}")
    String getWeaponSn(@PathVariable int firmId, @PathVariable int weaponId) {
        return weaponService.getWeaponSn(firmId, weaponId);
    }

    @GetMapping("/getFirms/{weaponId}")
    List<Firm> getFirms(@PathVariable int weaponId) {
        return weaponService.getFirms(weaponId);
    }
}
