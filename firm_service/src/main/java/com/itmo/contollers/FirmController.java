package com.itmo.contollers;

import com.itmo.services.FirmService;
import com.pip.entities.Firm;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Controller
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class FirmController {

    FirmService firmService;

    @GetMapping("/findAll")
    @ResponseBody
    List<Firm> findAllFirms() {
        return firmService.findAllFirms();
    }

    @ResponseBody
    @PostMapping("/reg")
    Firm registrateFirm(@RequestBody Firm firm) {
        firmService.addFirm(firm);
        return firm;
    }
}
