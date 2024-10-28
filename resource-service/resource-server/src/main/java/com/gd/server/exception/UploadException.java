package com.gd.server.exception;


import com.gd.core.exception.BusinessException;

public class UploadException extends BusinessException {

	public UploadException(String errorMessage) {
		super(errorMessage);
	}

}
