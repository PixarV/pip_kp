package com.itmo.contollers;

import com.itmo.services.PeopleService;
import com.pip.entities.Human;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {
    PeopleService peopleService;

    @GetMapping("/getAll")
    @ResponseBody
    List<Human> getAllHumans() {
        System.out.println("hey");
        return peopleService.findAllHumans();
    }
}
