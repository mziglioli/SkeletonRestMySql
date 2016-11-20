package com.controller.def;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.model.EntityJpa;
import com.service.ServiceDefault;

import lombok.Getter;

@RestController
@SuppressWarnings({ "rawtypes" })
public abstract class ControllerDefaultPublic<T extends ServiceDefault, E extends EntityJpa> {

	@Autowired
	@Getter
	protected T service;
}