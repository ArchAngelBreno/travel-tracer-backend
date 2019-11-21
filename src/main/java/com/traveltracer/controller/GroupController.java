package com.traveltracer.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveltracer.controller.dto.CreateActivitySpendRequest;
import com.traveltracer.controller.dto.CreateGroupRequest;
import com.traveltracer.controller.dto.GroupResponse;
import com.traveltracer.model.ActivitySpend;
import com.traveltracer.model.Group;
import com.traveltracer.service.ActivitySpendService;
import com.traveltracer.service.GroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Grupo", description = "Realiza todas as operações de grupo")
@RestController
@RequestMapping("/api/group/")
public class GroupController extends BaseController{

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private ActivitySpendService activitySpendService;

	@ApiOperation(value = "Cria um grupo")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@PostMapping
	public ResponseEntity<Group> createGroup(@Valid @RequestBody CreateGroupRequest group) {
		groupService.createGroup(group);
		URI uri = createUri(group.getUserId());
		
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Busca um grupo por ID", response = GroupResponse.class)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Id do grupo não existe")})
	@GetMapping("{id}")
	public ResponseEntity<GroupResponse> findGroupById(@PathVariable Long id) {
		 GroupResponse response = groupService.findGroupById(id);
		 return ResponseEntity.ok(response);
		 
	}
	
	@ApiOperation(value = "Adiciona usuario no grupo")
	@ApiResponses(value = {@ApiResponse(code = 204, message = "No content"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@PutMapping("{groupId}/user/{userId}")
	public ResponseEntity<Void> updateGroup(@PathVariable("groupId") Long groupId, @PathVariable("userId") Long userId) {
		groupService.updateGroup(groupId, userId);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Cria um gasto no grupo")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@PostMapping("{group_id}/spend")
	public ResponseEntity<ActivitySpend> createSpend(@Valid @RequestBody CreateActivitySpendRequest createActivitySpendRequest,
			@PathVariable("group_id") Long id)  {
		
		activitySpendService.createSpend(createActivitySpendRequest,id);
		URI uri = createUri(id);
		
		return ResponseEntity.created(uri).build();
	}
}
