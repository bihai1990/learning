package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class FeignServiceFallBack implements FeignService {
    @Override
    public String feignMethod(String name) {
        return "feign sorry " + name;
    }
}
