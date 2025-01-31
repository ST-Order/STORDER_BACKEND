package com.storder.order.user.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

	private final User user;

	public UserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	public String getEmail() {
		return user.getEmail();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return org.springframework.security.core.userdetails.UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return org.springframework.security.core.userdetails.UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return org.springframework.security.core.userdetails.UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return org.springframework.security.core.userdetails.UserDetails.super.isEnabled();
	}

	public User getUser() {
		return this.user;
	}
}
