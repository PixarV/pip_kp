package com.pip.controllers;

import com.pip.entities.Firm;
import com.pip.enums.FirmSpecializtion;
import com.pip.services.FirmService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Controller
@AllArgsConstructor
@RequestMapping("/firms")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class FirmController {

    FirmService firmService;

    @GetMapping
    @ResponseBody
    List<Firm> findAllEngines() {
        return firmService.findAllFirms();
    }

    @ResponseBody
    @GetMapping("/add")
    String addFirm() {
        Firm firm = Firm.builder()
                .title("probe_firm")
                .specialization(FirmSpecializtion.BOTH)
                .build();

        firmService.addFirm(firm);
        return "hey";
    }
}
