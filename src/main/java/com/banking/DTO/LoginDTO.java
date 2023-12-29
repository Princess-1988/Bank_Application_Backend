package com.banking.DTO;

import lombok.Data;

@Data
public class LoginDTO {

	private String usernameOrEmail;
	private String password;
}
