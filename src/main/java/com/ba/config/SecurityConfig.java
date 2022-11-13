package com.ba.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ba.security.CustomUserDetailService;
import com.ba.security.JwtAuthenticationEntryPoint;
import com.ba.security.JwtAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	// Here we write the urls which we want to give full access
	public static final String[] PUBLIC_URLS = {"/api/auth/**","/api-docs","/swagger-resources/**", "/swagger-ui/**", "/webjars/**"};
	
	@Autowired
	private CustomUserDetailService cudService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jaep;
	
	@Autowired
	private JwtAuthenticationFilter jaf;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		// This converts the default login form on the webpage into a JS prompt form
		http.csrf().disable().authorizeHttpRequests()
		.antMatchers(PUBLIC_URLS).permitAll().antMatchers(HttpMethod.GET).permitAll()
		.anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(this.jaep).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(this.jaf, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.cudService).passwordEncoder(passwordEncoder());
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}
	
	

}
