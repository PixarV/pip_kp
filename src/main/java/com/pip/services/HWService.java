package com.pip.services;

import org.springframework.stereotype.Service;

@Service
public class HWService {

    public String createHelloMessage(String name) {
        return "Hello " + name + "!";
    }
}
