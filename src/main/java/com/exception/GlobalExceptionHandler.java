package com.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j;

@Log4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public void handle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		String error = "{ \"error\": \"exception.default\" }";
		addResponse(response, String.valueOf(request.getRequestURL()), error, ex);
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public void handleDuplicateKeyException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		String error = "{ \"error\": \"exception.duplicateKey\" }";
		addResponse(response, String.valueOf(request.getRequestURL()), error, ex);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public void handleAccessDenied(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		String error = "{ \"error\": \"exception.accessDenied\" }";
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		addResponse(response, String.valueOf(request.getRequestURL()), error, ex);
	}

	private void addResponse(HttpServletResponse response, String url, String error, Exception ex) {
		response.setContentType("application/json");
		try {
			response.getOutputStream().println(error);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("Exception Occured:: URL=" + url + error);
		// ex.printStackTrace();
	}
}
