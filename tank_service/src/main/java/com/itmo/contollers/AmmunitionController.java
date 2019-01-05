package com.itmo.contollers;

import com.itmo.services.AmmunitionService;
import com.pip.entities.Ammunition;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
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
        ammunitionService.addAmmunition(ammunition);
    public Ammunition addAmmunition(@RequestBody Ammunition ammunition) {
        return ammunition;
    }

    @GetMapping("/get/{ammunitionId}")
    public Ammunition getAmmunitionById(@PathVariable int ammunitionId) {
        return ammunitionService.getAmmunitionById(ammunitionId);
    }

    @PutMapping("/update")
    public Ammunition updateAmmunition(@RequestBody Ammunition ammunition) {
        // TODO: 12/26/18 check entity not managed problem
        return ammunitionService.updateAmmunition(ammunition);
    }

    @DeleteMapping("/delete")
    public void deleteAmmunition(@RequestBody Ammunition ammunition) {
        ammunitionService.deleteAmmunition(ammunition);
    }

    @DeleteMapping("/delete/{ammunitionId}")
    public void deleteAmmunitionById(@PathVariable int ammunitionId) {
        ammunitionService.deleteAmmunitionById(ammunitionId);
    }


}
