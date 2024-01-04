package com.target.service.controllers.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    private String home() {
        return new String("Hello From Rules Engine");
    }

}
