package com.ba.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ba.entities.User;
import com.ba.exceptions.ResourceNotFoundException;
import com.ba.payloads.UserDto;
import com.ba.repositories.UserRepository;
import com.ba.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository urep;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Insert
	@Override
	public UserDto createUser(UserDto udto) {
		
		User user = this.dtoToUser(udto); // Converting dto to user
				
		User savedUser = this.urep.save(user); // Inserting user data in db
		
		return this.userToDto(savedUser); // Converting user back to dto

	}

	// Update
	@Override
	public UserDto updateUser(UserDto udto, Integer userId) {
		
		User user = this.urep.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId)); // Getting the user by id 
		
		user.setUser_name(udto.getUser_name());
		user.setUser_email(udto.getUser_email());
		user.setUser_password(udto.getUser_password());
		user.setUser_about(udto.getUser_about());
		
		User updatedUser = this.urep.save(user); // Updating user data in db
		
		return this.userToDto(updatedUser); // Converting user back to dto
		
	}

	// Get by id
	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.urep.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId)); // Getting the user by id 
		return this.userToDto(user);
	}

	
	// Get all
	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.urep.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList()); // This converts each User object in the list into UserDto object
		return userDtos;
	}

	
	// Delete
	@Override
	public void deleteUser(Integer userId) {
		User user = this.urep.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId)); // Getting the user by id 
		this.urep.delete(user);

	}
	
	// Function to convert UserDto object into User object
	public User dtoToUser(UserDto udto) {
		
		User user = this.modelMapper.map(udto, User.class); // This one line does the work of the below 5 lines
		
//		user.setUser_id(udto.getUser_id());
//		user.setUser_name(udto.getUser_name());
//		user.setUser_email(udto.getUser_email());
//		user.setUser_password(udto.getUser_password());
//		user.setUser_about(udto.getUser_about());
		return user;
	}
	
	// Function to convert User object into UserDto object
	public UserDto userToDto(User user) {
		
		UserDto udto = this.modelMapper.map(user, UserDto.class);
//		udto.setUser_id(user.getUser_id());
//		udto.setUser_name(user.getUser_name());
//		udto.setUser_email(user.getUser_email());
//		udto.setUser_password(user.getUser_password());
//		udto.setUser_about(user.getUser_about());
		return udto;
	}

}
