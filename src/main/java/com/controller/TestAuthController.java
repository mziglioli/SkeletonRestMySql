package com.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.UserService;
import com.util.StaticURL;

@RestController
@RequestMapping(value = StaticURL.TEST, produces = MediaType.APPLICATION_JSON_VALUE)
public class TestAuthController extends ControllerDefault<UserService, User> {

}