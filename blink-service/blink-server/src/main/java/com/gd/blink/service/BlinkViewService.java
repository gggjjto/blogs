package com.gd.blink.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gd.blink.pojo.BlinkView;
import com.gd.blink.pojo.BlinkViewListVO;

/**
 * Demo class
 *
 * @author jiang
 * @date 2024/10/5
 */
public interface BlinkViewService extends IService<BlinkView> {
    /**
     * 获取动态列表，以创建时间排序，获取最新的
     *
     * @param page       第几页
     * @param pageSize   页大小
     * @param schoolCode 院校代码
     * @return 动态列表数据
     */
    BlinkViewListVO getListByTime(int page, int pageSize, Integer schoolCode);

    /**
     * 获取个人的动态列表
     *
     * @param userId   用户id
     * @param page     第几页
     * @param pageSize 页大小
     * @return 动态列表数据
     */
    BlinkViewListVO getSelfList(int userId, int page, int pageSize);

}
