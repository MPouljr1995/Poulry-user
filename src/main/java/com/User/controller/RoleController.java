package com.User.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.User.model.Role;
import com.User.service.RoleService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/test")
public class RoleController {
	@Autowired
	RoleService roleService;
	//RoleRepository roleRepository;
	
	
	@GetMapping("/roles")
	public ResponseEntity<List<Role>> getAllRole(){
		return roleService.getAllRole();
		
	}
	
	

}
