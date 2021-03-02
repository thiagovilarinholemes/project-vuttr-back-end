package com.vuttr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.vuttr.security.AuthRequest;
import com.vuttr.security.JwtUtil;

@RestController
@RequestMapping("/api")
public class HomeController {

	@Autowired
    private JwtUtil jwtUtil;

    private AuthenticationManager authenticationManager;

	/* Request to Authentication */
	@PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPass())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }
	
	/* Access Denied */
	@GetMapping("/access-denied")
	public ResponseEntity<String> accessDenied() {
		String mensage = "Acesso negado!!!";
		return ResponseEntity.ok().body(mensage);
	}
	
	/* Logout */
	@GetMapping("/logout")
	public ResponseEntity<String> logout() {
		String mensage = "Logout efetuado com sucesso!!!";
		return ResponseEntity.ok().body(mensage);
	}


}
