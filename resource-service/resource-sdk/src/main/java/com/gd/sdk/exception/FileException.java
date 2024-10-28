package com.gd.sdk.exception;


import com.gd.core.exception.BusinessException;

/**
 * @author 阿杆
 */
public class FileException extends BusinessException {

	public FileException() {
		super("文件异常");
	}

	public FileException(String errorMessage) {
		super(errorMessage);
	}

}
