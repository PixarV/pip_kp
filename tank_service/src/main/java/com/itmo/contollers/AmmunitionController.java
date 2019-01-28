package com.itmo.contollers;

import com.itmo.services.AmmunitionService;
import com.pip.entities.Ammunition;
import com.pip.entities.Monster;
import com.pip.entities.Weapon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@Scope("session")
@AllArgsConstructor
@RequestMapping("/ammunitions")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AmmunitionController {

    AmmunitionService ammunitionService;

    @GetMapping("/getAll")
    public List<Ammunition> findAllAmmunitions() {
        return ammunitionService.findAllAmmunitions();
    }

    @PostMapping("/add")
    public Ammunition addAmmunition(@RequestBody Ammunition ammunition) {
        ammunitionService.addAmmunition(ammunition);
        return ammunition;
    }

    @GetMapping("/get/{ammunitionId}")
    public Ammunition getAmmunitionById(@PathVariable int ammunitionId) {
        return ammunitionService.getAmmunitionById(ammunitionId).get(0);
    }

    @PutMapping("/update")
    public void updateAmmunition(@RequestBody Ammunition ammunition) {
        ammunitionService.updateAmmunition(ammunition);
    }

    @DeleteMapping("/delete")
    public void deleteAmmunition(@RequestBody Ammunition ammunition) {
        ammunitionService.deleteAmmunition(ammunition);
    }

    @DeleteMapping("/delete/{ammunitionId}")
    public void deleteAmmunitionById(@PathVariable int ammunitionId) {
        ammunitionService.deleteAmmunitionById(ammunitionId);
    }

    @PutMapping("/addWeapon/{weaponId}")
    void addWeaponToAmmunition(@RequestBody Ammunition ammunition,
                               @PathVariable int weaponId) {
        ammunitionService.addWeapon(ammunition.getId(), weaponId);
    }

    @PutMapping("/removeAmmunitionWeapon/{ammunitionId}")
    void removeAmmuntionFromMtoM(@PathVariable int ammunitionId) {
        ammunitionService.removeAmmunitionFromMtoM(ammunitionId);
    }

    @GetMapping("/getWeapons/{ammunitionId}")
    List<Weapon> getWeapons(@PathVariable int ammunitionId) {
        return ammunitionService.getWeapons(ammunitionId);
    }

    @GetMapping("/getAllAmunitionWeapon")
    List<Monster> getAllAmunitionWeapon() {
        return ammunitionService.getAllAmunitionWeapon();
    }

}
