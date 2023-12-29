package com.banking.controller;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.DTO.LoginDTO;
import com.banking.DTO.SignupDTO;
import com.banking.entity.Role;
import com.banking.entity.User;
import com.banking.repository.RoleRepository;
import com.banking.repository.UserRepository;



@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDto )
	{
		try {
		System.out.println("Before saving loginDetails "+loginDto);
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Map<String, String> response=new HashMap<>();
		response.put("status","success");
		response.put("message","User singed-in successfully!!");
		return new ResponseEntity<>(authentication, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			System.out.println("Error in authentication");
			System.out.println(ex);
			return null;
		}
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupDTO signUpDto)
	{
		System.out.println("Before saving"+signUpDto);
		if(userRepository.existsByUsername(signUpDto.getUserName()))
		{
			return new ResponseEntity<>("Username is already taken!!", HttpStatus.BAD_REQUEST);
		}
		
		if(userRepository.existsByEmail(signUpDto.getEmail()))
		{
			return new ResponseEntity<>("Email is already taken!!", HttpStatus.BAD_REQUEST);
		}
		
		User user=new User();
		user.setEmail(signUpDto.getEmail());
		user.setName(signUpDto.getName());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		user.setUsername(signUpDto.getUserName());
		
		
		Role roles=roleRepository.findByName("ROLE_USER").get();
		user.setRoles(Collections.singleton(roles));
		
		System.out.println("Before saving"+user);
		userRepository.save(user);
		Map<String, String> response=new HashMap<>();
		response.put("status","success");
		response.put("message","User Registered Successfully");
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}

}
