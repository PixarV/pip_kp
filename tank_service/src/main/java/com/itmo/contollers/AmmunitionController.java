package com.itmo.contollers;

import com.itmo.services.AmmunitionService;
import com.pip.entities.Ammunition;
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

    @Setter
    @Getter
    private Ammunition ammunition = new Ammunition();

    @GetMapping("/getAll")
    public List<Ammunition> findAllAmmunitions() {
        return ammunitionService.findAllAmmunitions();
    }

    @PostMapping("/add")
    public Ammunition addAmmunition(@RequestBody Ammunition ammunition) {
        try {
            Ammunition tempAmmun = ammunition;
            ammunition = new Ammunition();
            ammunitionService.addAmmunition(tempAmmun);
        } catch (Exception e) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage("addForm:addButton", // id ratget form and target element
                                new FacesMessage("Error :(", e.getMessage()));
        }
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
