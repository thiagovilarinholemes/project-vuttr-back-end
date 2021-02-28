package com.vuttr.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vuttr.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private User user;

    public MyUserDetails(User user){
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

//        // Extract list of permissions (name)
//        for(Permission p: user.getRole().getPermissions()){
//            GrantedAuthority authority = new SimpleGrantedAuthority(p.getNamePermission());
//            authorities.add(authority);
//        }
//
//        // Extract list of authorizations (name)
//        for (Authorization a: user.getAuthorizations()){
//            GrantedAuthority authority = new SimpleGrantedAuthority(a.getNameAuthorization());
//            authorities.add(authority);
//        }

        // Extract list of roles (name)
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getNameRole());
        authorities.add(authority);

        return authorities;
    }

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
