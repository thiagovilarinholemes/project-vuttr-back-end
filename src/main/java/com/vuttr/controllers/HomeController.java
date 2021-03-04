package com.vuttr.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "Home Controller")
public class HomeController {
	
	/* Home */
	@ApiOperation(value = "Retorna a página inicial.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna a página inicial - Ex.: /api/home \n "),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@GetMapping(value = "/home", produces = "application/json" )
	public ResponseEntity<String> home() {
		String mensage = "Bem vindo a Vuttr!!!";
		return ResponseEntity.ok().body(mensage);
	}
	
	/* Access Denied */
	@ApiOperation(value = "Access denied")
	@GetMapping("/access-denied")
	public ResponseEntity<String> accessDenied() {
		String mensage = "Acesso negado!!!";
		return ResponseEntity.ok().body(mensage);
	}
	
	/* Logout */
	/* Home */
	@ApiOperation(value = "Faz Logout no sistema.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Faz Logout no sistema - Ex.: /api/logout \n "),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@GetMapping("/logout")
	public ResponseEntity<String> logout() {
		String mensage = "Logout efetuado com sucesso!!!";
		return ResponseEntity.ok().body(mensage);
	}


}
