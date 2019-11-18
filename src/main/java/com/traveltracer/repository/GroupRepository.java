package com.traveltracer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traveltracer.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
