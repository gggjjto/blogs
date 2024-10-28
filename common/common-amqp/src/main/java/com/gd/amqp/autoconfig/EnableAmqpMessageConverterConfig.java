package com.gd.amqp.autoconfig;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AmqpMessageConverterConfig.class)
public @interface EnableAmqpMessageConverterConfig {

}
