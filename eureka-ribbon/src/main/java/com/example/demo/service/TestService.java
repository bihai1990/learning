package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {

    @Autowired
    private RestTemplate restTemplate;

    public String testLoadBalance(String name) {
       return restTemplate.getForObject("http://eureka-client/hi?name=" + name, String.class);
    }
}
