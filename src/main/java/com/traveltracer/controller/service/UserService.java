package com.traveltracer.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traveltracer.model.User;
import com.traveltracer.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User createUser(User user) {
		return repository.save(user);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public Optional<User> getUserById(Long id) {
		return repository.findById(id);
	}

	public void deleteUser(Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
