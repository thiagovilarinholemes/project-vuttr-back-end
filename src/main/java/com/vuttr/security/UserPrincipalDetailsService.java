package com.vuttr.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vuttr.models.User;
import com.vuttr.repositories.UserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService{

	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		MyUserDetails userPrincipal = new MyUserDetails(user);
        return userPrincipal;		
	}	

}
