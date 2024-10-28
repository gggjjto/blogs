package com.gd.user.controller;


import com.gd.core.result.RestResult;
import com.gd.user.pojo.UserRegisterBO;
import com.gd.user.service.IRegisterService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册相关接口
 *
 * @author 阿杆
 */
@Slf4j
@RestController
@RequestMapping("/register")
@Validated
public class RegisterController {

	@Resource
	private IRegisterService registerService;

	/**
	 * 发送邮箱验证码请求
	 * 60s内重复请求无效
	 *
	 * @param mail 邮箱
	 */
	@PostMapping("/send-mail-verify")
	public RestResult<Object> sendMailVerify(@NotNull String mail) {
		log.debug("mail->{}", mail);
		return registerService.sendMailVerify(mail);
	}

	/**
	 * 注册账号，必须有正确的验证码才能注册成功
	 */
	@PostMapping("/register")
	public RestResult<Object> register(@Validated UserRegisterBO userRegisterBO) {
		return registerService.register(userRegisterBO);
	}

}
