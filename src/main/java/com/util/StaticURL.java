package com.util;

public class StaticURL {

	public static final String ROOT = "/";

	// PUBLIC
	public static final String PUBLIC_ALL = "/public/**";
	public static final String PUBLIC_ROOT = "/public/";
	public static final String PUBLIC = "/public";
	public static final String LOGIN = "/public/login";
	public static final String LOGOUT = "/public/logout";

	// CRUD
	public static final String FIND_BY_ID = "/{id}";
	public static final String FIND_ALL = "/all";
	public static final String EDIT = "/{id}";
	public static final String NEW = "/new";
	public static final String SAVE = "/";
	public static final String UPDATE = "/update/{id}";
	public static final String DELETE = "/delete/{id}";

	// SPECIFIC
	public static final String SIGNUP = "/signUp";
	public static final String USER = "/user";

}