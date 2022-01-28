package com.qa.dto_example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dto_example.data.dto.UserDTO;
import com.qa.dto_example.data.entity.User;
import com.qa.dto_example.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> getUsers() {
		ResponseEntity<List<UserDTO>> users = ResponseEntity.ok(userService.getAll());									 
		return users;
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
		UserDTO savedUser = userService.create(user);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/user/" + String.valueOf(savedUser.getId()));
		
		ResponseEntity<UserDTO> response = new ResponseEntity<UserDTO>(savedUser, headers, HttpStatus.CREATED);
		return response;
	}
}
