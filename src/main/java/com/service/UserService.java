package com.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.model.User;
import com.model.enuns.Status;
import com.repository.UserRepository;

@Service
public class UserService extends ServiceDefault<User, UserRepository> {

	public User findByUsername(String username) {
		return getRepository().findByUsername(username);
	}

	public Long getQtdUsers() {
		return getRepository().count();
	}

	public User getAuthenticatedUser() {
		try {
			return getUserFromUserLogado();
		} catch (Exception e) {
			e.printStackTrace();
			return new User(null, "", "Anonimous", "", Status.UNACTIVE, "", null);
		}
	}

	public User getUserFromUserLogado() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			Authentication auth = context.getAuthentication();
			if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
				return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
		}
		return null;
	}
}