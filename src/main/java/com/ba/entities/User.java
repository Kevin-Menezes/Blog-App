package com.ba.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_id_seq")
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", initialValue = 0, allocationSize=1)
	private int user_id;
	
	private String user_name;
	
	private String user_email;
	
	private String user_password;
	
	private String user_about;
	
	// Posts
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();

	// Role
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();

	
	// Getter Setter
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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User() {
		super();
	}

	public User(int user_id, String user_name, String user_email, String user_password, String user_about,
			List<Post> posts, Set<Role> roles) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_about = user_about;
		this.posts = posts;
		this.roles = roles;
	}
	

	//  ================ UserDetails methods below....UserDetails class is inbuilt
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<SimpleGrantedAuthority> authories = this.roles.stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return authories;
	}

	

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user_password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user_email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	

	
	
}
