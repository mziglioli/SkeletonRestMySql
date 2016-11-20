package com.controller;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.def.ControllerDefaultPublic;
import com.model.User;
import com.model.enuns.Authorities;
import com.model.enuns.Status;
import com.service.UserService;
import com.util.StaticURL;

@RestController
@RequestMapping(value = StaticURL.ADMIN)
public class LoginController extends ControllerDefaultPublic<UserService, User> {

	@PostMapping(value = StaticURL.SIGNUP)
	public void signUp(@RequestBody User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		getService().save(user);
	}

	@GetMapping(value = StaticURL.ROOT)
	public Collection<User> teste() {
		build();
		return getService().findAll();
	}

	public void build() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Collection<Authorities> authorities = new HashSet<>();
		authorities.add(Authorities.USER);
		getService().getRepository().deleteAll();

		User user2 = new User();
		user2.setName("mallmann");
		user2.setUsername("a.a.mallmann@gmail.com");
		user2.setPassword(encoder.encode("teste"));
		user2.setRoles(authorities);
		user2.setDescription("Senior Java Developer");
		user2.setStatus(Status.ACTIVE);
		getService().save(user2);

		User user = new User();
		user.setName("marcelo");
		user.setUsername("marceloziglioli@gmail.com");
		user.setDescription("Senior Java Developer - craft developer");
		user.setPassword(encoder.encode("teste"));
		user.setStatus(Status.ACTIVE);
		authorities.add(Authorities.ADMIN);
		user.setRoles(authorities);
		getService().save(user);
	}
}
