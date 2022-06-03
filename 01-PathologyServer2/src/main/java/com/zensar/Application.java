package com.zensar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zensar.dto.DiseaseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@SpringBootApplication
@RestController
@RequestMapping("/pathology-service")
public class Application {
	
	 @Autowired
	    @Lazy
	    private RestTemplate restTemplate;

	    public static final String PATHOLOGY_SERVICE="pathologyService";

	    private static final String BASEURL = "http://localhost:7083/disease";

	   private int attempt=1;

	  
	    @GetMapping("/displayDiseases")
	  @CircuitBreaker(name =PATHOLOGY_SERVICE,fallbackMethod = "getAllAvailableDisease")
	   @Retry(name = PATHOLOGY_SERVICE,fallbackMethod = "getAllAvailableDisease")
	    @PostConstruct 
	  	    public List<DiseaseDTO> displayOrders(@RequestParam("category") String category) {
	  	        String url = category == null ? BASEURL : BASEURL + "/" + category;
	  	       // System.out.println("retry method called "+attempt++ +" times "+" at "+new Date());
	  	        return restTemplate.getForObject(url, ArrayList.class);
	  	    }
	       

	    public List<DiseaseDTO> getAllAvailableProducts(Exception e){
	        return Stream.of(
	        		new DiseaseDTO("D1", "Cold", "Cold water bath"),
	    			new DiseaseDTO("D2", "Headache", "Panadol capsule"),
	    			new DiseaseDTO("D3", "Fiver", "Dabur Lipistat Capsule")
	               
	        ).collect(Collectors.toList());
	    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	 @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

}
