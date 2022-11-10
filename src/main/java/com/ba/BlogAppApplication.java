package com.ba;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
		System.out.println(this.passwordEncoder.encode("Keenan777"));
	}
	


}
