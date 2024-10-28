package com.gd.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.gd.clientResource.pojo.MailDTO;
import com.gd.tool.utils.RandomUtils;
import com.gd.user.config.UserConfig;

import com.gd.user.mapper.UserGeneralMapper;
import com.gd.user.mapper.UserMapper;
import com.gd.user.mapper.UserSafetyMapper;
import com.gd.user.mapper.UserViewMapper;
import com.gd.user.pojo.User;
import com.gd.user.pojo.UserGeneral;
import com.gd.user.pojo.UserSafety;
import com.gd.user.pojo.UserView;
import com.gd.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.gd.user.utils.RedisConstants.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangpeng
 * @since 2024-09-26
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserViewMapper, UserView> implements IUserService {

    @Resource
    private UserGeneralMapper userGeneralMapper;

    @Resource
    private UserSafetyMapper userSafetyMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder; // 加密

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private RedisTemplate<String, Object> objectRedisTemplate;

    @Override
    public User getById(Integer id) {
        String key = USER_SERVICE_INFO_KEY + id;
        User user = (User) objectRedisTemplate.opsForValue().get(key);
        if (user != null) {
            return user;
        }

        user = super.getById(id);
        if (user != null) {
            objectRedisTemplate.opsForValue().set(key, user, USER_SERVICE_INFO_TTL, TimeUnit.SECONDS);
        }
        return user;
    }

    @Override
    public User getByUsername(String username) {
        return lambdaQuery().eq(UserView::getUsername, username).one();
    }

    @Override
    public Map<Integer, UserView> getUserListMap(Set<Integer> userIdList) {
        if (userIdList == null || userIdList.isEmpty()) {
            return null;
        }
        List<UserView> userViews = query().getBaseMapper().selectBatchIds(userIdList);
        HashMap<Integer, UserView> userMap = new HashMap<>(userViews.size());
        for (UserView user : userViews) {
            userMap.put(user.getId(), user);
        }

        return userMap;
    }

    @Override
    public UserGeneral getUserGeneral(Integer userId) {
        if (userId == null) {
            return null;
        }
        LambdaQueryWrapper<UserGeneral> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserGeneral::getUserId, userId);
        return userGeneralMapper.selectOne(queryWrapper);
    }

    @Override
    public Map<Integer, UserGeneral> getUserGeneralListMap(List<Integer> userIdList) {
        if (userIdList == null || userIdList.isEmpty()) {
            return null;
        }
        LambdaQueryWrapper<UserGeneral> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(UserGeneral::getUserId, userIdList);
        List<UserGeneral> userGenerals = userGeneralMapper.selectList(wrapper);
        HashMap<Integer, UserGeneral> userMap = new HashMap<>(userIdList.size());
        for (UserGeneral user : userGenerals) {
            userMap.put(user.getId(), user);
        }
        return userMap;
    }

    @Override
    public boolean removeById(Integer id) {
        // 由于user表当中username字段为唯一索引，且该项目使用了逻辑删除
        // 所以我重写了userMapper的deleteById方法，将deleted字段值修改为id
        // user_safety也是一样
        if (userSafetyMapper.deleteById(id) == 1 && userMapper.deleteById(id) == 1) {
            return true;
        }
        throw new RuntimeException("数据库异常");
    }

    @Override
    public boolean checkPassword(Integer id, String password) {
        UserSafety userSafety = userSafetyMapper.selectById(id);
        return passwordEncoder.matches(password, userSafety.getPassword());
    }

    @Override
    public boolean updatePasswordById(Integer id, String password) {
        String encode = passwordEncoder.encode(password);
        return userSafetyMapper.updatePasswordById(id, encode) > 0;
    }

    @Override
    public String updateAvatar(Integer id, MultipartFile avatarFile) {
        log.debug("updateAvatar,id->{}, fileName->{}", id, avatarFile.getOriginalFilename());
        // 默认头像才需要更新数据库，非默认头像无需更新数据库
        User user = userMapper.selectById(id);
        if (UserConfig.DefaultAvatar.equals(user.getAvatarUrl())) {
            user.setAvatarUrl(user.getId() + "_" + user.getUsername());
            objectRedisTemplate.delete(USER_SERVICE_INFO_KEY + id);
            userMapper.updateById(user);
        }

        return "fail";
    }

    @Override
    public boolean updateNickname(Integer id, String nickname) {
        User user = new User();
        user.setId(id);
        user.setUsername(nickname);
        objectRedisTemplate.delete(USER_SERVICE_INFO_KEY + id);
        return userMapper.updateById(user) == 1;
    }

    @Override
    public boolean updateSchoolCode(Integer id, Integer schoolCode) {
        User user = new User();
        user.setId(id);
        user.setSchoolCode(schoolCode);
        objectRedisTemplate.delete(USER_SERVICE_INFO_KEY + id);
        return userMapper.updateById(user) == 1;
    }

    @Override
    public boolean updateMail(Integer id, String mail) {
        objectRedisTemplate.delete(USER_SERVICE_INFO_KEY + id);
        return userMapper.updateMailById(id, mail) == 1;
    }

    @Override
    public boolean sendMailVerify(Integer id) {
        // 0. 构造redis key
        String key = USER_SERVICE_MAIL_CODE_KEY + id;
        // 1. 查询当前用户的最近发送记录，通过ttl判断
        Long expire = redisTemplate.getExpire(key);
        // 允许的发送时间间隔
        int sendCodeInterval = 60;
        if (expire != null && USER_SERVICE_MAIL_CODE_TTL - expire < sendCodeInterval) {
            // 1.1 若时间不为空且未超过固定的时间间隔，则不允许发送
            throw new RuntimeException("发送频繁");
        }
        // 2 若为空或已经超过固定的时间间隔，则允许发送
        // 2.1 查询用户的邮箱
        UserSafety userSafety = userSafetyMapper.selectById(id);
        // 2.2 生成验证码，并构造发送邮件类型
        String code = RandomUtils.generator(6);
        MailDTO mailDto =new MailDTO();
        mailDto.setFrom("校园博客");
        mailDto.setTo(userSafety.getMail());
        mailDto.setSubject("验证码");
        mailDto.setText("亲爱的用户：\n" + "你正在操作你的账户信息，你的邮箱验证码为：" + code + "，此验证码有效时长5分钟，请勿转发他人。");
        // 3. 将验证码保存到redis
        redisTemplate.opsForValue().set(key, code, USER_SERVICE_MAIL_CODE_TTL, TimeUnit.SECONDS);
        // TODO 4. 发送邮件

        return false;
    }

    @Override
    public boolean checkMailVerify(Integer id,@Valid @NotNull String verify) {
        String code = redisTemplate.opsForValue().get(USER_SERVICE_MAIL_CODE_KEY + id);
        return verify.equals(code);
    }
}
