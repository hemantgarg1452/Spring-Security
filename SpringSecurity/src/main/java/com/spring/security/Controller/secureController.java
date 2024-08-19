package com.spring.security.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class secureController {

    @GetMapping("/start")
    public String hello(){
        return "Spring Security part-1";
    }

    //We can set our own username and password in app.properties file..
}
