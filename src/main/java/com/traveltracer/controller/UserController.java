package com.traveltracer.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveltracer.controller.dto.UserResponse;
import com.traveltracer.model.User;
import com.traveltracer.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserResponse>> getUsers() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
		UserResponse userResponse = service.getUserById(id);
		
		return ResponseEntity.ok(userResponse);
	}
	
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User createUser = service.createUser(user);
		URI uri = createUri(createUser.getId());
		
		return ResponseEntity.created(uri).build();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
		service.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
