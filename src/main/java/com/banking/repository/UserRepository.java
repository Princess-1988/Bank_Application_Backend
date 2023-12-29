package com.banking.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{
	
	   Optional<User> findByUsernameOrEmail(String username, String email);	
		Boolean existsByUsername(String username);
		Boolean existsByEmail(String email);

}
