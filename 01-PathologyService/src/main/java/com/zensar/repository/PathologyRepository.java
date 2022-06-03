package com.zensar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.zensar.pathologyService.model.Disease;

public interface PathologyRepository extends JpaRepository<Disease,Integer> {
	
	 List<Disease> findByDisease(String category);

}
