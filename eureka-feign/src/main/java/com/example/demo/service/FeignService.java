package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client", fallback = FeignServiceFallBack.class)
public interface FeignService {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String feignMethod(@RequestParam(value = "feign") String name);
}
