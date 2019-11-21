package com.traveltracer.controller;

import java.net.URI;
import java.util.ArrayList;
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
import com.traveltracer.service.exception.ApiError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "User", description = "Realiza todas as operações de usuario")
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{

	@Autowired
	private UserService service;

	@ApiOperation(value = "Busca todos os usuários do aplicativo", response = UserResponse.class)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna lista de usuários"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@GetMapping
	public ResponseEntity<List<UserResponse>> getUsers() {
		List<UserResponse> response = new ArrayList<UserResponse>();
		service.findAll().stream().forEach(user -> {
			response.add(new UserResponse(user));
		});
		return ResponseEntity.ok().body(response);
	}
	
	@ApiOperation(value = "Busca um unico usuário pelo seu ID", response = UserResponse.class)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna lista de usuários"),
			@ApiResponse(code = 404, message = "O id do usuário é inexistente", response = ApiError.class)})
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
		UserResponse userResponse = new UserResponse(service.getUserById(id));
		return ResponseEntity.ok().body(userResponse);
	}
	
	
	@ApiOperation(value = "Cria um usuario")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Usuario criado com sucesso"),
			@ApiResponse(code = 404, message = "O id do usuário é inexistente", response = ApiError.class)})
	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {
		User createUser = service.createUser(user);
		URI uri = createUri(createUser.getId());
		
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Deleta um usuario")
	@ApiResponses(value = {@ApiResponse(code = 204, message = "Usuario deletado com sucesso"),
			@ApiResponse(code = 404, message = "O id do usuário é inexistente", response = ApiError.class)})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
		service.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
