package com.mercadolibre.projetointegrador.config;

import com.mercadolibre.projetointegrador.exceptions.*;
import com.newrelic.api.agent.NewRelic;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ApiError> noHandlerFoundException(HttpServletRequest req, NoHandlerFoundException ex) {
		ApiError apiError = new ApiError("route_not_found", String.format("Route %s not found", req.getRequestURI()), HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(apiError.getStatus())
				.body(apiError);
	}

	@ExceptionHandler(value = { ApiException.class })
	protected ResponseEntity<ApiError> handleApiException(ApiException e) {
		Integer statusCode = e.getStatusCode();
		boolean expected = HttpStatus.INTERNAL_SERVER_ERROR.value() > statusCode;
		NewRelic.noticeError(e, expected);
		if (expected) {
			log.warn("Internal Api warn. Status Code: " + statusCode, e);
		} else {
			log.error("Internal Api error. Status Code: " + statusCode, e);
		}

		ApiError apiError = new ApiError(e.getCode(), e.getDescription(), statusCode);
		return ResponseEntity.status(apiError.getStatus())
				.body(apiError);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
			BadRequestException.class,
			org.springframework.dao.DuplicateKeyException.class,
			org.springframework.web.bind.support.WebExchangeBindException.class,
			org.springframework.http.converter.HttpMessageNotReadableException.class,
			org.springframework.web.server.ServerWebInputException.class
	})
	@ResponseBody
	public ApiError badRequest(Exception ex) {
		log.info("executing exception handler (REST)");
		return new ApiError(
				ex.getClass().getName(),
				ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value()
		);
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ValidationError> handleException(MethodArgumentNotValidException ex) {
		log.info("Error de validacion");
		return ex.getBindingResult().getAllErrors()
				.stream()
				.map(this::mapError)
				.collect(Collectors.toList());
	}

	private ValidationError mapError(ObjectError objectError) {
		if (objectError instanceof FieldError) {
			return new ValidationError(((FieldError) objectError).getField(),
					objectError.getDefaultMessage());
		}
		return new ValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
	}

	@ExceptionHandler({
			NotFoundException.class
	})
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiError notFoundRequest(NotFoundException ex) {
		ApiError error = new ApiError(
				ex.getClass().getName(),
				ex.getMessage(),
				HttpStatus.NOT_FOUND.value()
		);
		return error;

	}

	@ExceptionHandler({
			DataIntegrityViolationException.class
	})
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError dataIntegrityViolation(DataIntegrityViolationException ex) {

		log.error("Violacao de dados");

		ApiError error = new ApiError(
				ex.getClass().getName(),
				"Violacao de dados",
				HttpStatus.BAD_REQUEST.value()
		);
		return error;

	}
}