package com.traveltracer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traveltracer.controller.dto.UserResponse;
import com.traveltracer.model.User;
import com.traveltracer.repository.UserRepository;
import com.traveltracer.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User createUser(User user) {
		return repository.save(user);
	}

	public List<UserResponse> findAll() {
		List<User> users = repository.findAll();
		List<UserResponse> response = new ArrayList<UserResponse>();
		users.stream().forEach(user -> {
			response.add(new UserResponse(user));
		});

		return response;
	}

	public UserResponse getUserById(Long id) {
		Optional<User> user = repository.findById(id);

		if (!user.isEmpty()) {
			return new UserResponse(user);
		}

		throw new ObjectNotFoundException("O id: " + id + " não existe!");

	}

	public void deleteUser(Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
