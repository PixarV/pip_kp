package com.itmo.contollers;

import com.itmo.services.AmmunitionService;
import com.pip.entities.Ammunition;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Controller
@AllArgsConstructor
@RequestMapping("/ammunitions")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AmmunitionController {

    AmmunitionService ammunitionService;

    @ResponseBody
    @GetMapping("/getAll")
    List<Ammunition> findAllAmmunitions() {
        return ammunitionService.findAllAmmunitions();
    }

    @ResponseBody
    @PostMapping("/add")
    Ammunition addAmmunition(@RequestBody Ammunition ammunition) {
        ammunitionService.addAmmunition(ammunition);
        return ammunition;
    }

    @ResponseBody
    @GetMapping("/get/{ammunitionId}")
    Ammunition getAmmunition(@PathVariable int ammunitionId) {
        return ammunitionService.getAmmunitionById(ammunitionId);
    }

    @ResponseBody
    @PostMapping("/update")
    Ammunition updateAmmunition(@RequestBody Ammunition ammunition) {
        // TODO: 12/26/18 check entity not managed problem
        return ammunitionService.updateAmmunition(ammunition);
    }

    @ResponseBody
    @DeleteMapping("/delete")
    void deleteAmmunition(@RequestBody Ammunition ammunition) {
        ammunitionService.deleteAmmunition(ammunition);
    }

    @ResponseBody
    @DeleteMapping("/delete/{ammunitionId}")
    void deleteAmmunitionById(@PathVariable int ammunitionId) {
        ammunitionService.deleteAmmunitionById(ammunitionId);
    }


}
