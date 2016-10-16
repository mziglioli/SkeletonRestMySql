package com.util;

public class StaticDB {
	// DB configuration
	public static final String DB_NAME = "test2";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "marcelo12";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useUnicode=true";

	// hibernate confi
	public static final String DB_DDL = "hibernate.hbm2ddl.auto";
	public static final String DB_DDL_VALUE = "update";
	public static final String DB_DIALECT = "hibernate.dialect";
	public static final String DB_DIALECT_VALUE = "org.hibernate.dialect.MySQL5Dialect";
	public static final String DB_SHOW_SQL = "show_sql";
	public static final String DB_SHOW_SQL_VALUE = "true";
}