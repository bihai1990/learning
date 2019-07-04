package com.example.demo;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthApplication.class, args);
	}

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@RequestMapping("/hi")
	public String hi() {
		return restTemplate.getForObject("http://localhost:8703/test", String.class);
	}

	@RequestMapping("/info")
	public String info() {
		return "hi, i'm service-hi!";
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
