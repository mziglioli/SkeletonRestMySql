package com.security.user;

import java.util.Collection;

import com.model.UserAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityGroup {

	private String name;

	private String description;

	private Collection<UserAuthority> authorities;
}