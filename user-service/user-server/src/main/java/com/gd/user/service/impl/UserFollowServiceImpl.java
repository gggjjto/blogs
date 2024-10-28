package com.gd.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gd.user.mapper.FansViewMapper;
import com.gd.user.mapper.FollowViewMapper;
import com.gd.user.pojo.*;
import com.gd.user.mapper.UserFollowMapper;
import com.gd.user.service.IUserFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangpeng
 * @since 2024-09-27
 */
@Slf4j
@Service
public class UserFollowServiceImpl extends ServiceImpl<UserFollowMapper, UserFollow> implements IUserFollowService {

    @Resource
    private FollowViewMapper followViewMapper;

    @Resource
    private FansViewMapper fansViewMapper;

    @Override
    public boolean follow(int userId, int followId) {
        if (userId == followId) {
            throw new RuntimeException("不能关注自己！");
        }
        // 关注用户，查询是否存在关注记录，若不存在，则添加记录，若存在，则取消记录
        UserFollow userFollow =lambdaQuery().eq(UserFollow::getFansId, userId).eq(UserFollow::getFollowId, followId).one();
        // 用户关注使用状态区分，用户快速点击的时候会存在insert多条数据的情况。
        if (Objects.nonNull(userFollow)) {
            // 用户的关注使用状态区分，把关注状态取反
            boolean isFollow = userFollow.getStatus() == 1;
            lambdaUpdate().set(UserFollow::getStatus, isFollow ? 0 : 1)
                    .eq(UserFollow::getId, userFollow.getId()).update();
            return !isFollow;
        } else {
            UserFollow follow = new UserFollow();
            follow.setId(userId);
            follow.setFollowId(followId);
            follow.setStatus(1);
            userFollow.setCreateTime(new Timestamp(System.currentTimeMillis()));
            // 不存在，添加记录，返回true
            this.save(follow);
            return true;
        }
    }

    @Override
    public FollowViewListVO getFollowList(int userId, int page, int pageSize) {
        // 获取关注列表
        LambdaQueryWrapper<FollowView> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FollowView::getUserId, userId).eq(FollowView::getStatus, 1);
        IPage iPage = new Page(page, pageSize);
        followViewMapper.selectPage(iPage, wrapper);
        // 赋值返回
        FollowViewListVO listVO = new FollowViewListVO();
        BeanUtils.copyProperties(iPage, listVO);
        return listVO;
    }

    @Override
    public FansViewListVO getFansList(int userId, int page, int pageSize) {
        // 获取粉丝列表
        LambdaQueryWrapper<FansView> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FansView::getUserId, userId).eq(FansView::getStatus, 1);
        IPage iPage = new Page(page, pageSize);
        fansViewMapper.selectPage(iPage, wrapper);
        FansViewListVO listVO = new FansViewListVO();
        BeanUtils.copyProperties(iPage, listVO);
        return listVO;
    }

    @Override
    public List<Integer> getFollowIdList(int userId) {
        return followViewMapper.selectFollowIdByUserId(userId);
    }
}
