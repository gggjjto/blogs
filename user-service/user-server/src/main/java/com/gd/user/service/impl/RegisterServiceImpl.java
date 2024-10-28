package com.gd.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.gd.clientResource.MessageClient;
import com.gd.clientResource.pojo.MailDTO;
import com.gd.core.result.RestResult;
import com.gd.regex.RegexUtils;

import com.gd.tool.utils.RandomUtils;
import com.gd.user.mapper.UserSafetyMapper;
import com.gd.user.pojo.UserRegisterBO;
import com.gd.user.pojo.UserSafety;

import com.gd.user.service.IRegisterService;
import com.gd.user.service.IUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static com.gd.user.utils.RedisConstants.REGISTER_MAIL_CODE_KEY;
import static com.gd.user.utils.RedisConstants.REGISTER_MAIL_CODE_TTL;

/**
 * Demo class
 *
 * @author jiang
 * @date 2024/9/27
 */

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class RegisterServiceImpl extends ServiceImpl<UserSafetyMapper, UserSafety> implements IRegisterService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserSafetyMapper userSafetyMapper;

    @Resource
    private IUserService userService;

    @Resource
    private MessageClient messageClient;

    @Override
    public RestResult<Object> register(UserRegisterBO userRegisterBO) {
        // 1. 检查验证码是否正确
        if (!checkMailVerify(userRegisterBO.getMail(), userRegisterBO.getMailVerify())) {
            return RestResult.fail("验证码错误");
        }
        // 2.检查用户名是否已经存在
        if (userService.getByUsername(userRegisterBO.getUsername()) != null) {
            return RestResult.fail("用户名已存在");
        }
        // 3.检查手机号是否存在，因为暂时没有核实手机号码的验证码，所以就先将就一下
        String mobile = userRegisterBO.getMail();
        if (mobile == null || lambdaQuery().eq(UserSafety::getMobile, mobile).exists()) {
            return RestResult.fail("该手机号已被注册");
        }

        return null;
    }

    @Override
    public RestResult<Object> sendMailVerify(String mailAddress) {
        // 1.校验邮箱
        if (!RegexUtils.isEmailInvalid(mailAddress)) {
            // 1.2.如果不符合，返回错误信息
            return RestResult.fail("不存在的邮箱");
        }
        // 2.检查上一次发送时间，此处并未记录真实发送时间，仅通过验证码的ttl来判断
        String key = REGISTER_MAIL_CODE_KEY + mailAddress;
        int sendCodeInterval = 60;
        Long keyExpire = stringRedisTemplate.getExpire(key);
        log.debug("check REGISTER_MAIL_CODE_TTL,{}", keyExpire);
        if (keyExpire != null && REGISTER_MAIL_CODE_TTL - keyExpire < sendCodeInterval) {
            // 2.1不合符，拒绝发送
            return RestResult.fail("发送频繁");
        }
        // 3.检查邮箱是否已经被注册
        if (userSafetyMapper.selectByMail(mailAddress) != null) {
            return RestResult.fail("该邮箱已被注册");
        }
        // 4.满足条件，生成验证码
        String code = RandomUtils.randomNumber(6).toString();
        // 4.1.存入redis
        stringRedisTemplate.opsForValue().set(key, code, REGISTER_MAIL_CODE_TTL, TimeUnit.SECONDS);
        // 5.发送邮件
        MailDTO mailDTO = new MailDTO();
        mailDTO.setFrom("校园博客");
        mailDTO.setTo(mailAddress);
        mailDTO.setSubject("校园博客注册验证码");
        mailDTO.setText("亲爱的用户：\n" + "你正在注册校园博客，你的邮箱验证码为：" + code + "，此验证码有效时长5分钟，请勿转发他人。");
        // 发送邮件return
        messageClient.sendMail(mailDTO);
        return RestResult.ok();
    }

    private boolean checkMailVerify(@Valid @NotNull String mailAddress, @NotNull String code) {
        String key = REGISTER_MAIL_CODE_KEY + mailAddress;
        String verify = stringRedisTemplate.opsForValue().get(key);
        return code.equals(verify);
    }
}
