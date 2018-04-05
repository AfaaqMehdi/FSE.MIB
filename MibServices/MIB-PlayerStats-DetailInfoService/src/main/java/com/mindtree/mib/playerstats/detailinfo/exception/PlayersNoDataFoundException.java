package com.mindtree.mib.playerstats.detailinfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(value=HttpStatus.NO_CONTENT)
public class PlayersNoDataFoundException   extends HttpStatusCodeException  {
	private static final long serialVersionUID = 1L;

	public PlayersNoDataFoundException(HttpStatus status) {
		super(status);
	}
}


 