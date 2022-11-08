package com.daimler.emst2.frw.webexceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.daimler.emst2.frw.model.ErrorResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = Logger.getLogger(RestResponseEntityExceptionHandler.class.getName());

	/*
	 * ******************************************* Spring data exceptions: START
	 ********************************************/
	/**
	 * Data Integrity may happen because of invalid data from client - BUT also
	 * because of a bug. So we can only guess -> 409 seams better than 500.
	 * 
	 * @return 409 - CONFLICT
	 */
	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
			WebRequest request) {
        String bodyOfResponse = "Interner Server Fehler. Kontaktieren Sie ihren Administrator.";
        return handleExceptionLoggingDelegate(ex,
                ErrorResponse.createError(bodyOfResponse, extractImportantExceptionErrors(ex)), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	/**
	 * The rest of data access exceptions.
	 * 
	 * @param ex
	 * @param request
	 * @return 500 - INTERNAL_SERVER_ERROR
	 */
	@ExceptionHandler(value = { DataAccessException.class })
	protected ResponseEntity<Object> handleDataAccess(DataAccessException ex, WebRequest request) {
        String bodyOfResponse = "Interner Server Fehler. Kontaktieren Sie ihren Administrator.";
        return handleExceptionLoggingDelegate(ex,
                ErrorResponse.createError(bodyOfResponse, extractImportantExceptionErrors(ex)), new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}
	/*
	 * ******************************************* Spring data exceptions: END
	 ********************************************/

	/*
	 * ******************************************* CUSTOM EMST exceptions: START
	 ********************************************/

	// XXX Example custom exception
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return 406 - NOT_ACCEPTABLE
	 */
	@ExceptionHandler(value = { NotAcceptableRuntimeException.class })
	protected ResponseEntity<Object> handleNotAcceptable(NotAcceptableRuntimeException ex, WebRequest request) {
		String msg = ex.getMessage();
        if (ex.hasMessages()) {
            return handleExceptionLoggingDelegate(ex,
                    ex.getResponseMessage(),
                    new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
        }
        return handleExceptionLoggingDelegate(ex,
                ErrorResponse.createError(msg, extractImportantExceptionErrors(ex)),
                new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
	}

	/*
	 * ******************************************* CUSTOM EMST exceptions: END
	 ********************************************/

	/*
	 * ******************************************* ELSE -> DEFAULT
	 ********************************************/

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return 500 - INTERNAL_SERVER_ERROR
	 */
	@ExceptionHandler(value = { RuntimeException.class })
	protected ResponseEntity<Object> handleOther(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Interner Server Fehler. Kontaktieren Sie ihren Administrator.";
        return handleExceptionLoggingDelegate(ex,
                ErrorResponse.createError(bodyOfResponse, extractImportantExceptionErrors(
                        ex)),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
				request, Level.SEVERE);
	}

	/**
	 * OptimisticLockingFailure happens by Version mismatch.
	 * 
	 * @return 409 - CONFLICT
	 */
	@ExceptionHandler(value = { OptimisticLockingFailureException.class })
	protected ResponseEntity<Object> handleOptimisticLocking(OptimisticLockingFailureException ex, WebRequest request) {
		return handleExceptionLoggingDelegate(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	protected ResponseEntity<Object> handleExceptionLoggingDelegate(Exception ex, @Nullable Object body,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionLoggingDelegate(ex, body, headers, status, request, Level.WARNING);
	}

	protected ResponseEntity<Object> handleExceptionLoggingDelegate(Exception ex, @Nullable Object body,
			HttpHeaders headers, HttpStatus status, WebRequest request, Level logLevel) {
		LOG.log(logLevel, ex.getMessage() + ExceptionUtils.getStackTrace(ex), ex);
        return handleExceptionInternal(ex, body, headers, status, request);
	}

    protected String extractImportantExceptionErrors(Throwable ex) {
		StringBuilder sbSpecialErrors = new StringBuilder();
		sbSpecialErrors.append(" ").append(ExceptionUtils.getRootCauseMessage(ex));
		return sbSpecialErrors.toString();
	}
}
