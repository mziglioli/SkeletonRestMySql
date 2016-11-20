package com.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.repository.CategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { PersistenceConfigTest.class, RepositoryConfiguration.class })
public class CategoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void init() {
		Long qtde = categoryRepository.count();
		assertEquals(Long.valueOf(0), qtde);
	}

	@Test
	@SqlGroup({
		//@formatter:off	
		@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:category_insert.sql"),
		@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:category_clean.sql") 
		//@formatter:on	
	})
	public void insert() {
		Long qtde = categoryRepository.count();
		assertEquals(Long.valueOf(2), qtde);
	}
}