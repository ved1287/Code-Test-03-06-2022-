package com.zensar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.dto.DiseaseDTO;


public interface PathologyRepository extends JpaRepository<DiseaseDTO,Integer> {
	
	 List<DiseaseDTO> findByDisease(String category);

}
