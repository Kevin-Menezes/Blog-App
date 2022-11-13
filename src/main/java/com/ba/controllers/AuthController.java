package com.ba.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ba.entities.User;
import com.ba.exceptions.ApiException;
import com.ba.payloads.JwtAuthRequest;
import com.ba.payloads.JwtAuthResponse;
import com.ba.payloads.UserDto;
import com.ba.security.JwtTokenHelper;
import com.ba.services.UserService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jtHelper;
	
	@Autowired
	private UserDetailsService udService;
	
	@Autowired
	private AuthenticationManager aManager;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService uService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jaRequest){
		
		this.authenticate(jaRequest.getUser_name(),jaRequest.getUser_password()); // Getting the username and password using request
		
		UserDetails userDetails = this.udService.loadUserByUsername(jaRequest.getUser_name());
		
		String token = this.jtHelper.generateToken(userDetails); // Generating a token using JwtTokenHelper class

		JwtAuthResponse jaResponse = new JwtAuthResponse();
		jaResponse.setToken(token);
		jaResponse.setUdto(this.modelMapper.map((User) userDetails, UserDto.class));
		return new ResponseEntity<JwtAuthResponse>(jaResponse, HttpStatus.OK); // Returning the token using response	
		
	}

	private void authenticate(String username, String password) {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

		try {

			this.aManager.authenticate(authenticationToken);

		} 
		catch (BadCredentialsException e) {
			System.out.println("Invalid details");
			throw new ApiException("Invalid username or password");
		}
	}
	
	// This registers the user and gives him/her roles (Kevin777 , Keenan777)
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
		UserDto registeredUser = this.uService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}
	
	

}
