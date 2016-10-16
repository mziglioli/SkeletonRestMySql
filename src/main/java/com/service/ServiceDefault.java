package com.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.EntityJpa;
import com.util.StaticValue;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
public abstract class ServiceDefault<T extends EntityJpa, R extends JpaRepository<T, Long>> {

	@Autowired
	protected R repository;

	@Autowired
	protected UserService userService;

	public final T save(T entity) {
		if (entity.getId() != null) {
			return update(entity);
		}
		log("save " + entity.getClass().getName());
		beforeInsert();
		repository.save(entity);
		afterInsert();
		return entity;
	}

	private T update(T entity) {
		log("update " + entity.getClass().getName());
		beforeUpdate();
		repository.save(entity);
		afterUpdate();
		return entity;
	}

	public final void delete(T entity) {
		log("delete " + entity.getClass().getName());
		beforeDelete();
		repository.delete(entity);
		afterDelete();
	}

	public Collection<T> findAll() {
		return repository.findAll();
	}

	protected void afterInsert() {

	}

	protected void afterUpdate() {

	}

	protected void afterDelete() {

	}

	protected void beforeInsert() {

	}

	protected void beforeUpdate() {

	}

	protected void beforeDelete() {

	}

	protected void log(String msg) {
		log.info(msg + StaticValue.LOG_SEPARATOR + userService.getAuthenticatedUser().toString());
	}
}