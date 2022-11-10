package com.ba.services;

import java.util.List;

import com.ba.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto udto);
	
	UserDto updateUser(UserDto udto,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);

}
