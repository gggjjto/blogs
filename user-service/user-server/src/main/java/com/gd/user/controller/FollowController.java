package com.gd.user.controller;


import com.gd.core.exception.BusinessException;
import com.gd.user.pojo.FansViewListVO;
import com.gd.user.pojo.FollowViewListVO;
import com.gd.user.service.IUserFollowService;
import com.gd.web.auth.AuthHelper;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户关注接口
 *
 * @author 阿杆
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Validated
public class FollowController {

	/**
	 * 默认每页20条
	 */
	private final int pageSize = 20;

	@Resource
	private IUserFollowService userFollowService;

	/**
	 * 获取关注列表
	 *
	 * @param page 当前页
	 * @return 关注列表数据
	 */
	@GetMapping("/follow")
	public FollowViewListVO getFollowList(@RequestParam(defaultValue = "1") int page, Integer userId) {
		if (userId == null) {
			userId = AuthHelper.getCurrentUserId();
			if (userId == null) {
				throw new BusinessException("参数异常");
			}
		}
		return userFollowService.getFollowList(userId, page, pageSize);
	}

	/**
	 * 获取粉丝列表
	 *
	 * @param page 当前页
	 * @return 粉丝列表数据
	 */
	@GetMapping("/fans")
	public FansViewListVO getFansList(@RequestParam(defaultValue = "1") int page, Integer userId) {
		if (userId == null) {
			userId = AuthHelper.getCurrentUserId();
			if (userId == null) {
				throw new BusinessException("参数异常");
			}
		}
		return userFollowService.getFansList(userId, page, pageSize);
	}

	/**
	 * 关注其他用户
	 *
	 * @param followId 被关注的用户id
	 * @return 关注状态，若成功则为true，取关则为false
	 */
	@PostMapping("/follow")
	public Boolean doFollow(@NotNull Integer followId) {
		Integer id = AuthHelper.getCurrentUserIdOrExit();
		return userFollowService.follow(id, followId);
	}

	/**
	 * 获取关注列表id
	 */
	@GetMapping("/followId")
	public List<Integer> getFollowIdList(@NotNull Integer userId) {
		return userFollowService.getFollowIdList(userId);
	}

}
