package com.gd.redis.autoconfig;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Redis默认序列化配置开关
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedisSerializeDefaultConfig.class)
public @interface EnableRedisSerialize {

}
