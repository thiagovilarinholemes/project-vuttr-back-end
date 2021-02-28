package com.vuttr.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.vuttr.models.Role;
import com.vuttr.models.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "O campo NOME é obrigatório!")
    private String name;
	
	@NotBlank(message = "O campo USER NAME é obrigatório!")
    private String email;
	
	@NotBlank(message = "O campo SENHA é obrigatório!")
    private String password;
	
    private UserStatus userStatus;     
	

	private Role role;
	
}
