package com.example.ssbdiscoveryconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SsbDiscoveryConsumer {

	public static void main(String[] args) {
		SpringApplication.run(SsbDiscoveryConsumer.class, args);
	}

	@GetMapping
	@RequestMapping("/")
	@ResponseBody
	public String getGreeting() {
		ResponseEntity<String> responseEntity1 = getRestTemplate().getForEntity("http://SimpleSpringBoot1/",
				String.class);

		ResponseEntity<String> responseEntity2 = getRestTemplate().getForEntity("http://myNodeApp/",
				String.class);

		String greeting = "Hello from Eureka Consumer: <br />  " +
				"Here is the message from SimpleSpringBootWithDiscovery app (SimpleSpringBoot1): " + responseEntity1.getBody() + "<br />" +
				"Here is the message from Simple-Node-App (myNodeApp): " + responseEntity2.getBody();

		return greeting;
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
