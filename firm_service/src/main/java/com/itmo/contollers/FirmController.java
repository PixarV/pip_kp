package com.itmo.contollers;

import com.itmo.services.FirmService;
import com.pip.entities.Firm;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@AllArgsConstructor
@RequestMapping("/firms")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class FirmController {

    FirmService firmService;

    @GetMapping("/findAll")
    List<Firm> findAllFirms() {
        return firmService.findAllFirms();
    }

    @PostMapping("/reg")
    Firm registrateFirm(@RequestBody Firm firm) {
        firmService.addFirm(firm);
        return firm;
    }

    @GetMapping("/get/{firmId}")
    Firm getFirm(@PathVariable int firmId) {
        return firmService.getFirmById(firmId);
    }

    @PutMapping("/update")
    void updateFirm(@RequestBody Firm firm) {
        firmService.updateFirm(firm);
    }

    @DeleteMapping("/delete")
    void deleteFirm(@RequestBody Firm firm) {
        firmService.deleteFirm(firm);
    }

    @DeleteMapping("/delete/{firmId}")
    void deleteFirmById(@PathVariable int firmId) {
        firmService.deleteFirmById(firmId);
    }
}
