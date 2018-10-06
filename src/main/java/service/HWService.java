package service;

import javax.enterprise.context.Dependent;

@Dependent
public class HWService {

    public String createHelloMessage(String name) {
        return "Hello " + name + "!";
    }
}
