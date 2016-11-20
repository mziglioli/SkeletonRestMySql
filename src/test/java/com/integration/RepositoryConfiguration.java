package com.integration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.model.EntityJpa;
import com.repository.RepositoryPackage;

@Configuration
@EntityScan(basePackageClasses = { EntityJpa.class })
@EnableJpaRepositories(basePackageClasses = { RepositoryPackage.class })
@EnableTransactionManagement
@Transactional
public class RepositoryConfiguration {
}