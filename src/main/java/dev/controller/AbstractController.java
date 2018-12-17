package dev.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.exception.ClientErrorInformation;
import dev.exception.FunctionalException;

public abstract class AbstractController {

	public AbstractController() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Handler qui renvoie un message d'erreur au serveur si une exception de
	 * type {@link BusinessException} se produit. Cet handler a du sens dans le
	 * cadre d'une utilisation avec un front asynchrone (angular par exemple)
	 * 
	 * @param req
	 *            requête HTTP
	 * @param ex
	 *            exception
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(FunctionalException.class)
	public ResponseEntity<ClientErrorInformation> handleException(HttpServletRequest req, Exception ex) {
		ClientErrorInformation error = new ClientErrorInformation(ex.getMessage(), req.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
