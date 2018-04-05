package com.mindtree.mib.playerstats.detailinfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class PlayerFetchException extends HttpStatusCodeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayerFetchException(HttpStatus status) {
		super(status);
	}
	
}
