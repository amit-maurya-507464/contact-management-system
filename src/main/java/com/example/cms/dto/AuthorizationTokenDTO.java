package com.example.cms.dto;

import lombok.Data;

@Data
public class AuthorizationTokenDTO {

	public AuthorizationTokenDTO(String userName, String authToken) {
		this.userName=userName;
		this.authToken=authToken;
	}

	String authToken;
	String userName;

}
