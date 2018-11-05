package com.pip.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HWController {

    @ResponseBody
    @RequestMapping("/hello")
    String sayHello() {
        return "hello, ritt";
    }

    @RequestMapping("/show")
    String goToShow(Model model) {
        return "show.html";
    }
}
