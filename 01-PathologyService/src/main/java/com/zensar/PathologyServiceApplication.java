package com.zensar;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.zensar.pathologyService.model.Disease;
import com.zensar.repository.PathologyRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@RequestMapping("/diseases")
public class PathologyServiceApplication {
	

	@Autowired
    private PathologyRepository pathologyRepository;

    @PostConstruct
    public void initDiseaseTable() {
       pathologyRepository.saveAll(Stream.of(
    		   new Disease("D1", "Cold", "Cold water bath"),
    			new Disease("D2", "Headache", "Panadol capsule"),
    			new Disease("D3", "Fiver", "Dabur Lipistat Capsule")
                ).
                collect(Collectors.toList()));
    }

	@GetMapping

	public List<Disease> getOrders(){
		return pathologyRepository.findAll();
	}
	@GetMapping("/{disease}")
	public List<Disease> getOrdersByDiseases(@PathVariable String disease){
		return pathologyRepository.findByDisease(disease);
	}

	public static void main(String[] args) {
		SpringApplication.run(PathologyServiceApplication.class, args);
	}

}
