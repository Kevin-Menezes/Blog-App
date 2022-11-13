package com.ba;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ba.config.AppConstants;
import com.ba.entities.Role;
import com.ba.repositories.RoleRepository;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository rrep;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
	
	
	// Model mapper is used to convert one entity into another
	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}

	// This just gives a encoded value of Keenan777
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Keenan777 = "+this.passwordEncoder.encode("Keenan777"));
	
		try {

			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_NORMAL");

			List<Role> roles = List.of(role, role1);

			List<Role> result = this.rrep.saveAll(roles);

			result.forEach(r -> {
				System.out.println(r.getName());
			});

		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	}
	


}
