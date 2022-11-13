package com.ba.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ba.entities.Role;

public class UserDto {
	
	private int user_id;
	
	@NotEmpty
	@Size(min=3,message="Username must be min 3 characters!")
	private String user_name;
	
	@Email
	private String user_email;
	
	@NotEmpty
	@Size(min=3,max=10,message="Password must be min 3 characters and max of 10 characters!")
	//@Pattern(regexp = "^[0-9]{3,10}$")
	private String user_password;
	
	@NotEmpty
	@Size(min=3,message="About must be min 3 characters!")
	private String user_about;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_about() {
		return user_about;
	}
	public void setUser_about(String user_about) {
		this.user_about = user_about;
	}
	
	public Set<RoleDto> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	
	public UserDto() {
		super();
	}
	
	public UserDto(int user_id, String user_name, String user_email, String user_password, String user_about,Set<RoleDto> roles) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_about = user_about;
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "UserDto [user_id=" + user_id + ", user_name=" + user_name + ", user_email=" + user_email
				+ ", user_password=" + user_password + ", user_about=" + user_about + ", roles=" + roles + "]";
	}
}
