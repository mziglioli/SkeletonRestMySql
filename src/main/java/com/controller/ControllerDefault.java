package com.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.model.EntityJpa;
import com.service.ServiceDefault;
import com.util.StaticURL;
import com.util.StaticValue;

import lombok.Getter;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public abstract class ControllerDefault<T extends ServiceDefault, E extends EntityJpa> {

	@Autowired
	@Getter
	protected T service;

	@RequestMapping(value = StaticURL.ROOT, method = RequestMethod.POST)
	@PreAuthorize(StaticValue.HAS_ROLE_USER)
	@ResponseStatus(code = HttpStatus.OK)
	protected void save(@RequestBody E entity) {
		getService().save(entity);
	}

	@RequestMapping(value = StaticURL.FIND_BY_ID, method = RequestMethod.PUT)
	@PreAuthorize(StaticValue.HAS_ROLE_USER)
	@ResponseStatus(code = HttpStatus.OK)
	protected void update(@RequestBody E entity) {
		getService().save(entity);
	}

	@RequestMapping(value = StaticURL.FIND_ALL, method = RequestMethod.GET)
	@PreAuthorize(StaticValue.HAS_ROLE_USER)
	protected Collection<E> findAll() {
		return getService().findAll();
	}

	@RequestMapping(value = StaticURL.FIND_BY_ID, method = RequestMethod.GET)
	@PreAuthorize(StaticValue.HAS_ROLE_USER)
	protected E find(@PathVariable String id) {
		return (E) getService().getRepository().findOne(id);
	}

	@RequestMapping(value = StaticURL.FIND_BY_ID, method = RequestMethod.DELETE)
	@PreAuthorize(StaticValue.HAS_ROLE_USER)
	@ResponseStatus(code = HttpStatus.OK)
	protected void delete(@PathVariable String id) {
		getService().getRepository().delete(id);
	}

}