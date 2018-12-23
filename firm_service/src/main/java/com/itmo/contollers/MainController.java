package com.itmo.contollers;

import com.pip.entities.Firm;
import com.itmo.services.FirmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {
    FirmService firmService;

    @GetMapping("/getAll")
    @ResponseBody
    List<Firm> getAllFirms() {
        System.out.println("hey");
        return firmService.findAllFirms();
    }
}
