package com.integration;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.User;
import com.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { PersistenceConfigTest.class, RepositoryConfiguration.class })
public class ClientRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void checkUsers() {
		build();

		List<User> users = userRepository.findAll();
		assertEquals(2, users.size());
	}

	public void build() {
		// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// Collection<UserAuthority> authorities = new HashSet<>();
		// authorities.add(new UserAuthority(Authorities.USER.getRole()));
		// // userService.getRepository().deleteAll();
		//
		// Long teste = userRepository.count();
		//
		// User user2 = new User();
		// user2.setName("mallmann");
		// user2.setUsername("a.a.mallmann@gmail.com");
		// user2.setPassword(encoder.encode("teste"));
		// user2.setAuthorities(authorities);
		// user2.setDescription("Senior Java Developer");
		// user2.setStatus(Status.ACTIVE);
		// userRepository.save(user2);
		//
		// User user = new User();
		// user.setName("marcelo");
		// user.setUsername("marceloziglioli@gmail.com");
		// user.setDescription("Senior Java Developer - craft developer");
		// user.setPassword(encoder.encode("teste"));
		// user.setStatus(Status.ACTIVE);
		// user.setAuthorities(authorities);
		// authorities.add(new UserAuthority(Authorities.ADMIN.getRole()));
		// userRepository.save(user);

	}
}