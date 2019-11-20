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
import com.traveltracer.controller.dto.UpdateGroupRequest;
import com.traveltracer.model.ActivitySpend;
import com.traveltracer.model.Group;
import com.traveltracer.service.ActivitySpendService;
import com.traveltracer.service.GroupService;

@RestController
@RequestMapping("/api/group/")
public class GroupController extends BaseController{

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private ActivitySpendService activitySpendService;

	@PostMapping
	public ResponseEntity<Group> createGroup(@Valid @RequestBody CreateGroupRequest group) {
		groupService.createGroup(group);
		URI uri = createUri(group.getUserId());
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GroupResponse> findGroupById(@PathVariable Long id) {
		 GroupResponse response = groupService.findGroupById(id);
		 return ResponseEntity.ok(response);
		 
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> updateGroup(@Valid @RequestBody UpdateGroupRequest updateGroupRequest, @PathVariable Long id) {
		groupService.updateGroup(updateGroupRequest, id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PostMapping("{group_id}/spend")
	public ResponseEntity<ActivitySpend> createSpend(@Valid @RequestBody CreateActivitySpendRequest createActivitySpendRequest,
			@PathVariable("group_id") Long id)  {
		
		activitySpendService.createSpend(createActivitySpendRequest,id);
		URI uri = createUri(id);
		
		return ResponseEntity.created(uri).build();
	}
}
