package com.gd.user.controller;


import com.gd.core.result.RestResult;
import com.gd.sdk.type.FileType;
import com.gd.sdk.utils.FileUtils;
import com.gd.user.pojo.User;
import com.gd.user.pojo.UserGeneral;
import com.gd.user.pojo.UserView;
import com.gd.user.service.IUserService;
import com.gd.web.anno.RequestLimit;
import com.gd.web.auth.AuthHelper;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiangpeng
 * @since 2024-09-26
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;
    /**
     * 获取公开信息
     *
     * @param id 用户名id
     */
    @GetMapping
    @RequestLimit
    public User getByUserId(Integer id) {
        Integer getId = null;
        Integer userId = AuthHelper.getCurrentUserId();
        if (id == null && userId != null) {
            getId = userId;
        } else if (id != null) {
            getId = id;
        }
        return userService.getById(getId);
    }

    /**
     * 批量获取用户信息
     */
    @PostMapping("/list")
    public Map<Integer, UserView> getUserList(@RequestParam List<Integer> userIdList) {
        return userService.getUserListMap(new HashSet<>(userIdList));
    }

    /**
     * 查询用户创作数据
     *
     * @param userId 用户id
     * @return 批量用户统计数据
     */
    @GetMapping("/general")
    public UserGeneral getUserGeneral(@RequestParam Integer userId) {
        return userService.getUserGeneral(userId);
    }


    /**
     * 批量查询用户各项数据统计
     *
     * @param userIdList 用户id列表
     * @return 批量用户统计数据
     */
    @GetMapping("/general/map")
    public Map<Integer, UserGeneral> getUserGeneralList(@RequestParam List<Integer> userIdList) {
        return userService.getUserGeneralListMap(userIdList);
    }

    /**
     * 修改昵称
     *
     * @param nickname 昵称
     */
    @PutMapping("/nickname")
    public RestResult<Object> updateNickname(@NotNull String nickname) {
        Integer userId = AuthHelper.getCurrentUserIdOrExit();
        if (userService.updateNickname(userId, nickname)) {
            return RestResult.ok();
        }
        return RestResult.fail();
    }

    /**
     * 修改头像
     *
     * @param avatarFile 文件流
     */
    @PutMapping("/avatar")
    public RestResult<String> updateAvatar(@NotNull MultipartFile avatarFile) {
        Integer id = AuthHelper.getCurrentUserIdOrExit();
        log.debug("updateAvatar,fileSize->{}", avatarFile.getSize());
        // 检查文件，小于1Mib ,仅支持JPEG和PNG
        FileUtils.checkFile(avatarFile, 1024 * 1024L, FileType.JPEG, FileType.PNG);
        String avatar = userService.updateAvatar(id, avatarFile);
        if (avatar != null) {
            return RestResult.ok(avatar);
        } else {
            return RestResult.fail("上传失败");
        }
    }

    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    @PutMapping("/password")
    public RestResult<Object> updatePassword(@NotNull String oldPassword, @NotNull String newPassword) {
        Integer id = AuthHelper.getCurrentUserIdOrExit();
        if (userService.checkPassword(id, oldPassword)) {
            boolean result = userService.updatePasswordById(id, newPassword);
            return RestResult.ok(result);
        }
        return RestResult.fail("修改失败");
    }
}
