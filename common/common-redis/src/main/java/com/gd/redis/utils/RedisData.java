package com.gd.redis.utils;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class RedisData {

	LocalDateTime expireTime;

	Object data;

}
