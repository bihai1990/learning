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
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@RequestMapping("/hi")
	public String home(){
		return "hi i'm miya!";
	}

	@RequestMapping("/test")
	public String info(){
		return restTemplate.getForObject("http://localhost:8702/info",String.class);
	}

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
