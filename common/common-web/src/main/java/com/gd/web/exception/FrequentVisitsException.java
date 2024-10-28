package com.gd.web.exception;

import com.gd.core.exception.BusinessException;

public class FrequentVisitsException extends BusinessException {

	public FrequentVisitsException(String errorMessage) {
		super(errorMessage);
	}

	public FrequentVisitsException() {
		super("访问频繁");
	}

}
