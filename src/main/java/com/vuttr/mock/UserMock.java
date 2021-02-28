package com.vuttr.mock;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vuttr.models.Role;
import com.vuttr.models.User;
import com.vuttr.models.enums.UserStatus;
import com.vuttr.repositories.RoleRepository;
import com.vuttr.repositories.UserRepository;

/* Keep this comment annotated so as not to insert data into the database */
//@Configuration
public class UserMock implements CommandLineRunner{
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		
		/* Delete Data */
//		userRepository.deleteAll();
//		roleRepository.deleteAll();
		
		/* Insert Roles Database */		
		Role r1 = new Role(null, "ROLE_ADMIN", "Função de Administrador do Sistema");
		Role r2 = new Role(null, "ROLE_MANAGER", "Função de Gerente do Sistema");
		Role r3 = new Role(null, "ROLE_USER", "Função de Usuário do Sistema");
		roleRepository.saveAll(Arrays.asList(r1, r2, r3));
		
		/* Insert Users Database */		
		User u1 = new User(null, "Thiago Vilarinho Lemes", "thiago", passwordEncoder.encode("123"), UserStatus.ACTIVE, r1);
		User u2 = new User(null, "Carina Lima", "carina", "123", UserStatus.ACTIVE, r2);
		User u3 = new User(null, "Spack Rella", "spack.rella", "123", UserStatus.BLOCKED, r3);
		User u4 = new User(null, "Rosimar Vilarinho", "rosimar.lemes", "123", UserStatus.SUSPENDED, r3);
		User u5 = new User(null, "Juscelino Lemes", "juscelino.lemes", "123", UserStatus.BLOCKED, r3);
		User u6 = new User(null, "Carol V.", "carol.lemes", "123", UserStatus.BLOCKED, r2);
		User u7 = new User(null, "Luíz Souza", "luiz.fulano", "123", UserStatus.BLOCKED, r2);
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6, u7));	
		
		
	}

}
