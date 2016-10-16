package com.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.extern.log4j.Log4j;

@Log4j
@ControllerAdvice
public class GlobalExceptionHandler {

	// @Autowired
	// private InterceptorMenu interceptorMenu;
	//
	// @ExceptionHandler
	// public void handle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
	// log.info("Exception Occured:: URL=" + request.getRequestURL());
	// ex.printStackTrace();
	// // ModelAndView model = new ModelAndView();
	// // interceptorMenu.addMenu(model);
	// // addError(model, "exception", new String[] { "name" }, "error.duplicate.key");
	// // model.setViewName(Pages.NOT_FOUND);
	// // return model;
	// }
	//
	// private void addError(ModelAndView model, String objectName, String[] args, String message) {
	// List<ObjectError> errors = new ArrayList<>();
	// errors.add(new ObjectError(objectName, null, args, message));
	// model.addObject("errors", errors.stream().collect(Collectors.toList()));
	// }
}