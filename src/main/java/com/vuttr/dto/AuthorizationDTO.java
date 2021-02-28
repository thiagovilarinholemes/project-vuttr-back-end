package com.vuttr.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
		
    private Long id;
    
    @NotBlank(message = "O campo NOME é obrigatório!")
    private String name;
    
    @NotBlank(message = "O campo DESCRIÇÃO é obrigatório!")
    private String description;


}
