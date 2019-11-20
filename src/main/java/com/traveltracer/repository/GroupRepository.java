package com.traveltracer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveltracer.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{

	
	
}
