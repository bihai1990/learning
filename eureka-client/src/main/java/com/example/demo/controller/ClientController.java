package com.example.demo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(@RequestParam(value = "name", defaultValue = "test") String name) {
        return "hi " + name + ", i am from " + port;
    }

    public String hiError(String name) {
        return "hi " + name + ", error!";
    }
}
