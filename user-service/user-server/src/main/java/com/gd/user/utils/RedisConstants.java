package com.gd.user.utils;


public class RedisConstants {

	public static final String REGISTER_MAIL_CODE_KEY = "user:register:mailCode:";

	public static final Long REGISTER_MAIL_CODE_TTL = 300L;

	public static final String USER_SERVICE_MAIL_CODE_KEY = "user:userService:mailCode:";

	public static final Long USER_SERVICE_MAIL_CODE_TTL = 300L;

	public static final String USER_SERVICE_INFO_KEY = "user:userService:info:";

	public static final Long USER_SERVICE_INFO_TTL = 30 * 60L;

}
