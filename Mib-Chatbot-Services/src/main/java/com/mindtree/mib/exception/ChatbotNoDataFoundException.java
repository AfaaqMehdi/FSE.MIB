/**
 * 
 */
package com.mindtree.mib.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * @author M1038389
 *
 */

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class ChatbotNoDataFoundException extends HttpStatusCodeException {

	private static final long serialVersionUID = 1L;

	public ChatbotNoDataFoundException(HttpStatus status) {
		super(status);
	}
}
