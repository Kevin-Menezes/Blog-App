package com.ba.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ba.payloads.ApiResponse;
import com.ba.payloads.UserDto;
import com.ba.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService uService;
	private final static Logger log = LoggerFactory.getLogger(UserController.class);
	
	// Create
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto udto){
		UserDto createudto = this.uService.createUser(udto);
		return new ResponseEntity<>(createudto,HttpStatus.CREATED);
	}
	
	// Update
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto udto,@PathVariable("userId") Integer userId){
		UserDto updatedUser = this.uService.updateUser(udto, userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	// Delete
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
		this.uService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted!",true),HttpStatus.OK);
	}
	
	// Read All
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.uService.getAllUsers());
	}
	
	// Read one
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId){
		return ResponseEntity.ok(this.uService.getUserById(userId));
	}
	
}
