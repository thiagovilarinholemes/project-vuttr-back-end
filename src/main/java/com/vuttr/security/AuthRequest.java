package com.vuttr.security;

import lombok.Data;

@Data
public class AuthRequest {

	private String email;
	private String pass;
}
