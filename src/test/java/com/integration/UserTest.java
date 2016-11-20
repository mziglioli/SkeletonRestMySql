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
public class UserTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void checkUsers() {
		List<User> users = userRepository.findAll();
		assertEquals(2, users.size());
	}
}