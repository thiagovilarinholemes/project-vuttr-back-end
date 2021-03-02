package com.vuttr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vuttr.repositories.UserRepository;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserPrincipalDetailsService userPrincipalDetailsService; 
	@Autowired
	UserRepository userRepository;
	
    /* Configurations for authentication - login */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userPrincipalDetailsService);//.passwordEncoder(new BCryptPasswordEncoder());

    }

    /* Configuration for authorization - access */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /* Remove csrf and state in session because in jwt we do not need them */
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)	
                .and()
                
                /* Add jwt filters (1. authentication, 2. authorization) */
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.userRepository))
                .authorizeRequests()
                
                /* configure access roles */
                .antMatchers(HttpMethod.POST, "/login").permitAll()  
                .antMatchers("/api/logout").permitAll()
                .antMatchers("/api/tools/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_MANAGER")  	
                .antMatchers("/api/roles/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
                .antMatchers("/api/users/**").hasAnyAuthority("ROLE_ADMIN")
                                                
                /* Block if you are not authenticated */
                .antMatchers("/**").denyAll()
                .anyRequest().authenticated() 
        		.and()
                
        		/* Access denied */
		        .exceptionHandling()
		        .accessDeniedPage("/api/access-denied")
        		.and()
        		        		        		
        		/* Logout */
		        .logout()
		        .logoutUrl("/logout")
		        .logoutSuccessUrl("/api/logout")
		        .invalidateHttpSession(true)
		        .deleteCookies("Authorization");
    }

    /* Configuration for static resources - CSS, Materialize... */
    @Override
    public void configure(WebSecurity web) throws Exception {
    }
    
    /* Encryption type */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }	
}
