package com.controller;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.model.UserAuthority;
import com.model.enuns.Authorities;
import com.model.enuns.Status;
import com.service.UserService;
import com.util.StaticURL;

@RestController
@RequestMapping(value = StaticURL.PUBLIC, produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = StaticURL.SIGNUP, method = RequestMethod.POST)
	public void signUp(@RequestBody User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userService.save(user);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<User> teste() {
		build();
		return userService.findAll();
	}

	public void build() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Collection<UserAuthority> authorities = new HashSet<>();
		authorities.add(new UserAuthority(Authorities.USER.getRole()));
		userService.getRepository().deleteAll();

		User user2 = new User();
		user2.setName("mallmann");
		user2.setUsername("a.a.mallmann@gmail.com");
		user2.setPassword(encoder.encode("teste"));
		user2.setAuthorities(authorities);
		user2.setDescription("Senior Java Developer");
		user2.setStatus(Status.ACTIVE);
		userService.save(user2);

		User user = new User();
		user.setName("marcelo");
		user.setUsername("marceloziglioli@gmail.com");
		user.setDescription("Senior Java Developer - craft developer");
		user.setPassword(encoder.encode("teste"));
		user.setStatus(Status.ACTIVE);
		user.setAuthorities(authorities);
		authorities.add(new UserAuthority(Authorities.ADMIN.getRole()));
		userService.save(user);
	}
}
