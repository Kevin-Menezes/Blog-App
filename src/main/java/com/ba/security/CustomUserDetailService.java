package com.ba.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ba.entities.User;
import com.ba.exceptions.ResourceNotFoundException;
import com.ba.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository urep;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.urep.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User","email : "+username,0));
		return user;
	}

}
